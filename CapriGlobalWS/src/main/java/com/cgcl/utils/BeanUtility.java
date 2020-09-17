package com.cgcl.utils;

import java.io.Serializable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *
 * <b>File Name :</b> <i>BeanUtils.java</i> <br>
 * <b>Description :</b>
 * <p>
 * This class contains the activities of skill program. <br/>
 * <br/>
 * <b>Copyright :</b> Copyright© yyyy description
 * </p>
 * <h1>Version History :</h1>
 *
 * Date : 20-06-2013<br/>
 * Description : First Draft
 *
 * @version 1.0.0.1
 * @since 1.0.0.0.1
 * @author visruth
 *
 */
public class BeanUtility implements Serializable {

    /**
     *      
     */
    private static final long serialVersionUID = -7043339954874582588L;

    /**
     *  This method trims all String variables defined in the bean if they have
     *  corresponding getter setter methods. <br/>
     *  Eg : BeanUtils beanUtils=new BeanUtils();<br/>
     *  StudentVO studentVO=new StudentVO();<br/>
     *  studentVO.setName("   foo   ");<br/>
     *  studentVO.setAddress("   bar   ");<br/>
     *  beanUtils.trimAllStrings(studentVO);<br/>
     *  System.out.println(studentVO.getName());//prints foo<br/>
     *  System.out.println(studentVO.getAddress());//prints bar<br/>
     * 
     *  @param beanObject
     *  @throws Exception
     *              If a variable has its getter method defined but not setter
     *              method will throw NoSuchMethodException instance as
     *              Exception.
     *  @author visruth
     */
    public void trimAllStrings(Object beanObject) throws Exception {
        Exception noSuchMethodException      = null;
        boolean   throwNoSuchMethodException = false;

        if (beanObject != null) {
            Method[] methods = null;

            try {
                methods = beanObject.getClass().getMethods();
            } catch (SecurityException e) {
                throw new Exception(e);
            }

            if (methods != null) {
                for (Method method : methods) {
                    String methodName = method.getName();

                    if (!methodName.equals("getClass")) {
                        String returnType       = method.getReturnType().toString();
                        String commonMethodName = null;

                        if (methodName.startsWith("get") && "class java.lang.String".equals(returnType)) {
                            commonMethodName = methodName.replaceFirst("get", "");

                            String returnedValue = null;

                            try {
                                returnedValue = (String) method.invoke(beanObject);
                            } catch (IllegalArgumentException e) {
                                e.printStackTrace();

                                throw e;
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();

                                throw e;
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();

                                throw e;
                            }

                            if (returnedValue != null) {
                                StringBuilder setterMethodName = new StringBuilder();

                                setterMethodName.append("set");
                                setterMethodName.append(commonMethodName);

                                Method setterMethod = null;

                                try {
                                    setterMethod = beanObject.getClass()
                                                             .getMethod(setterMethodName.toString(), String.class);

                                    if (setterMethod != null) {
                                        setterMethod.invoke(beanObject, (returnedValue.trim().toUpperCase()));
                                       
                                    }
                                } catch (SecurityException e) {
                                    e.printStackTrace();

                                    throw e;
                                } catch (NoSuchMethodException e) {
                                    e.printStackTrace();

                                    if (!throwNoSuchMethodException) {
                                        noSuchMethodException = e;
                                    }

                                    throwNoSuchMethodException = true;
                                } catch (IllegalArgumentException e) {
                                    e.printStackTrace();

                                    throw e;
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();

                                    throw e;
                                } catch (InvocationTargetException e) {
                                    e.printStackTrace();

                                    throw e;
                                }
                            }
                        }
                    }
                }
            }
        }

        if (throwNoSuchMethodException && (noSuchMethodException != null)) {
            throw noSuchMethodException;
        }
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
