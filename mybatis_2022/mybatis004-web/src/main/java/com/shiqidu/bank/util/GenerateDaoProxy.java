package com.shiqidu.bank.util;

import org.apache.ibatis.javassist.ClassPool;
import org.apache.ibatis.javassist.CtClass;
import org.apache.ibatis.javassist.CtMethod;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 动态生产Dao的实现类
 */
public class GenerateDaoProxy {
    /**
     * 动态生产Dao的实现类，并且将实现类的对象创建出来并返回
     *
     * @param daoInterface Dao接口
     * @return dao接口的实现类的实例化对象
     */
    public static Object generate(SqlSession sqlSession, Class daoInterface) {
        // 类池
        ClassPool pool = ClassPool.getDefault();
        // 制造类
        CtClass ctClass = pool.makeClass(daoInterface.getName() + "Impl");
        // 制造接口
        CtClass ctInterface = pool.makeInterface(daoInterface.getName());
        // 将接口添加到类中
        ctClass.addInterface(ctInterface);
        // 实现接口的所有方法
        Method[] declaredMethods = daoInterface.getDeclaredMethods();
        Arrays.stream(declaredMethods).forEach(method -> {
            StringBuilder methodCode = new StringBuilder();
            // Account selectByActNo(String actNo);
            methodCode.append("public ");
            // 返回值类型
            methodCode.append(method.getReturnType().getName());
            methodCode.append(" ");
            // 方法名
            methodCode.append(method.getName());
            methodCode.append("(");
            // 参数类型
            Class<?>[] parameterTypes = method.getParameterTypes();
            for (int i = 0; i < parameterTypes.length; i++) {
                methodCode.append(parameterTypes[i].getName());
                methodCode.append(" arg");
                methodCode.append(i);
                if (i < parameterTypes.length - 1) {
                    methodCode.append(",");
                }
            }
            methodCode.append(")");

            // 方法体代码
            methodCode.append("{");
            methodCode.append("org.apache.ibatis.session.SqlSession sqlSession = com.shiqidu.bank.util.SqlSessionUtil.openSession();");
            String sqlId = daoInterface.getName() + "." + method.getName();
            SqlCommandType sqlCommandType = sqlSession.getConfiguration().getMappedStatement(sqlId).getSqlCommandType();
            if (sqlCommandType == SqlCommandType.INSERT) {
                methodCode.append("sqlSession.insert(\"");
                methodCode.append(sqlId);
                methodCode.append("\",");
                methodCode.append("arg0");
                methodCode.append(");");
            } else if (sqlCommandType == SqlCommandType.UPDATE) {
                methodCode.append("return sqlSession.update(\"");
                methodCode.append(sqlId);
                methodCode.append("\",");
                methodCode.append("arg0");
                methodCode.append(");");
            } else if (sqlCommandType == SqlCommandType.DELETE) {
                methodCode.append("sqlSession.delete(\"");
                methodCode.append(sqlId);
                methodCode.append("\",");
                methodCode.append("arg0");
                methodCode.append(");");
            } else if (sqlCommandType == SqlCommandType.SELECT) {
                methodCode.append("return (");
                methodCode.append(method.getReturnType().getName());
                methodCode.append(")sqlSession.selectOne(\"");
                methodCode.append(sqlId);
                methodCode.append("\",");
                methodCode.append("arg0");
                methodCode.append(");");
            }
            methodCode.append("}");

            // 创建方法
            try {
                CtMethod ctMethod = CtMethod.make(methodCode.toString(), ctClass);
                ctClass.addMethod(ctMethod);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Object object;
        try {
            Class<?> clazz = ctClass.toClass();
            object = clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return object;
    }
}
