package com.springcloud.ms.controller.spring.ioctest.test;

import com.springcloud.ms.controller.spring.ioctest.ioc.BeanDefinition;
import com.springcloud.ms.controller.spring.ioctest.ioc.PropertyValue;
import com.springcloud.ms.controller.spring.ioctest.ioc.RuntimeBeanReference;
import com.springcloud.ms.controller.spring.ioctest.ioc.TypedStringValue;
import com.springcloud.ms.controller.spring.ioctest.po.User;
import com.springcloud.ms.controller.spring.ioctest.service.UserService;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: yaorp
 */
public class TestIOC {
    /**
     * 存储BeanDefinition的容器
     */
    private Map<String, BeanDefinition> beanDefinitions = new HashMap<>();
    /**
     * 存储Bean实例的容器
     */
    private Map<String, Object> singletonObjects = new HashMap<>();

    @Before
    public void before(){
        // 进行BeaanDefination的注册
        String location = "beans.xml";
        // 获取beans.xml的输入流
        InputStream inputStream = getInputStream(location);
        Document document = getDocument(inputStream);

        // 按照spring的配置语意来进行解析,把解析出来的信息存储到BeanDefinitions中
        loadBeanDefinitions(document.getRootElement());

    }

    @Test
    public void test(){
        // （相当于从spring ioc容器中获取bean）这里只是完成了bean的创建
        UserService userService = (UserService) getBean("userService");
        Map<String, Object> map = new HashMap<>();
        map.put("username","千场老亚瑟");
        List<User> users = userService.queryUsers(map);
        System.out.println(users);
    }

    private Object getBean(String name) {
        // 2.bean实例的创建流程
        Object bean = this.singletonObjects.get(name);
        if (bean != null) {
            return bean;
        }

        // 从BeanDefinitions中获取BeanDefinition
        BeanDefinition bd = this.beanDefinitions.get(name);
        if (bd == null) {return null; }

        // 判断是单例还是多例
        if ("singleton".equals(bd.getScope())) {
            bean = createBean(bd);
            this.singletonObjects.put(name, bean);
        } else if ("prototype".equals(bd.getScope())) {
            bean = createBean(bd);
        }
        return bean;

    }

    private Object createBean(BeanDefinition bd) {
        // bean的实例化
        Object bean = createBeanInstance(bd);
        // bean的依赖注入
        populateBean(bean, bd);
        initializeBean(bean,bd);

        return bean;
    }

    private void initializeBean(Object bean, BeanDefinition bd) {
        // init-method标签属性对应的初始化方法的调用
        invokeInitMethod(bean,bd);
    }

    private void invokeInitMethod(Object bean, BeanDefinition bd) {
        String initMethod = bd.getInitMethod();
        if (initMethod == null || initMethod.equals("")) {
            return;
        }

        Class<?> clazzType = bd.getClazzType();
        Method method = null;
        try {
            // 执行initMethod方法
            method = clazzType.getDeclaredMethod(initMethod);
            method.invoke(bean);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void populateBean(Object bean, BeanDefinition bd) {
        List<PropertyValue> pvs = bd.getPropertyValues();
        for (PropertyValue pv : pvs) {
            String name = pv.getName();
            Object value = pv.getValue();

            Object valueToUse = resoleValue(value);
            setProperty(bean,name, valueToUse);
        }

    }

    private void setProperty(Object bean, String name, Object valueToUse) {
        Class<?> aClass = bean.getClass();
        try {
            // todo 思考，spring中是通过属性还是setter方法去依赖注入的呢
            Field field = aClass.getDeclaredField(name);
            field.setAccessible(true);
            field.set(bean,valueToUse);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Object resoleValue(Object value) {
        if (value instanceof RuntimeBeanReference) {
            RuntimeBeanReference reference = (RuntimeBeanReference) value;
            String name = reference.getRef();

            // todo 此处会发生循环依赖问题
            return getBean(name);
        } else if (value instanceof TypedStringValue) {
            TypedStringValue typedStringValue = (TypedStringValue) value;
            String stringValue = typedStringValue.getValue();
            Class targetType = typedStringValue.getTargetType();
            if (targetType !=null) {
                return handleType(stringValue, targetType);
            }
            return stringValue;
        }

        return null;
    }

    private Object handleType(String stringValue, Class targetType) {
        if (targetType == Integer.class){
            return Integer.parseInt(stringValue);
        } else if (targetType == Double.class) {
            return Double.parseDouble(stringValue);
        } else if (targetType == String.class) {
            return stringValue;
        }
        return null;
    }

    private Object createBeanInstance(BeanDefinition bd) {
        // 获取构造器
        Constructor constructor = getConstructor(bd);

        // 通过反射去创建bean实例
        Object bean = newObject(constructor);
        return bean;
    }

    private Object newObject(Constructor constructor) {
        try {
            Object bean = constructor.newInstance();
            return bean;
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private Constructor getConstructor(BeanDefinition bd) {
        Class<?> clazzType = bd.getClazzType();
        try {
            Constructor<?> constructor = clazzType.getDeclaredConstructor();
            return constructor;
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadBeanDefinitions(Element rootElement) {
         // 获取beans元素
        List<Element> elements = rootElement.elements();
        for (Element element : elements) {
            String name = element.getName();
            if ("bean".equals(name)){
                // 解析bean元素
                parseDefaultElement(element);
            }else {
                System.out.println("不支持这种格式");
            }
        }
    }

    private void parseDefaultElement(Element element) {
        String id = element.attributeValue("id");

        String clazzName = element.attributeValue("class");
        String scope = element.attributeValue("scope");
        scope = scope == null || scope.equals("") ? "singleton" : scope;
        String initMethod = element.attributeValue("init-method");
        Class aClass = resolveClassType(clazzName);
        id = id == null || id.equals("")? aClass.getSimpleName() : id;

        BeanDefinition bd = new BeanDefinition(clazzName, id);
        bd.setScope(scope);
        bd.setInitMethod(initMethod);
        List<Element> elements = element.elements();
        parsePropertyElements(bd, elements);

        this.beanDefinitions.put(id,bd);
    }

    private void parsePropertyElements(BeanDefinition bd, List<Element> elements) {
        for (Element element : elements) {
            parsePropertyElement(bd, element);
        }
    }

    private void parsePropertyElement(BeanDefinition bd, Element element) {
        String name = element.attributeValue("name");
        String value = element.attributeValue("value");
        String ref = element.attributeValue("ref");

        if ((value == null || value.isEmpty()) && (ref == null || ref.isEmpty())) {
            return;
        }

        if (value!= null && !value.isEmpty()) {
            TypedStringValue typedStringValue = new TypedStringValue(value);
            Class targetType = resolveTargetType(bd.getClazzType(), name);
            typedStringValue.setTargetType(targetType);
            PropertyValue pv = new PropertyValue(name, typedStringValue);

            bd.addPropertyValue(pv);
        }else if (ref != null && !ref.isEmpty()){
            RuntimeBeanReference reference = new RuntimeBeanReference(ref);
            PropertyValue pv = new PropertyValue(name, reference);
            bd.addPropertyValue(pv);
        }
    }

    private Class resolveTargetType(Class<?> clazzType, String name) {
        try {
            Field field = clazzType.getDeclaredField(name);
            return field.getType();
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    private Class resolveClassType(String clazzName) {
        try {
            return Class.forName(clazzName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private Document getDocument(InputStream inputStream) {
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(inputStream);
            return document;
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    private InputStream getInputStream(String location) {
        return this.getClass().getClassLoader().getResourceAsStream(location);
    }


}
