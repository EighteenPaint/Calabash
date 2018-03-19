package com.hbh.Utils;


import com.hbh.bean.MethodParameterBean;
import com.hbh.controller.HelloController;
import com.sun.xml.internal.ws.org.objectweb.asm.*;
import com.sun.xml.internal.ws.org.objectweb.asm.Type;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.*;
import java.net.URL;

/**
 * Created by chenbin@megaeyes.com on 2018/3/7 0007.
 */
public class MethodASM {
    public static MethodParameterBean[] getMethodParamNames(final Method method) throws IOException {
        final String methodName = method.getName();
        final Class[] methodParameterTypes = method.getParameterTypes();
        final int methodParameterCount = methodParameterTypes.length;
        final String className = method.getDeclaringClass().getName();
        final Class clazz = method.getDeclaringClass();
        final boolean isStatic = Modifier.isStatic(method.getModifiers());
        final MethodParameterBean[] methodParametersNames = new MethodParameterBean[methodParameterCount];
        InputStream is =clazz.getClassLoader().getResourceAsStream(className.replace(".","/") + ".class");
        ClassReader cr = new ClassReader(is);
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        cr.accept(new ClassAdapter(cw) {
            public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {

                MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);

                final Type[] argTypes = Type.getArgumentTypes(desc);
                //参数类型不一致
                if (!methodName.equals(name) || !matchTypes(argTypes, methodParameterTypes)) {
                    return mv;
                }
                for(int i = 0;i<argTypes.length;i++){
                    methodParametersNames[i] = new MethodParameterBean(argTypes[i],methodParameterTypes[i]);

                }
                return new MethodAdapter(mv) {
                    public void visitLocalVariable(String name, String desc, String signature, Label start, Label end, int index) {
                        //如果是静态方法，第一个参数就是方法参数，非静态方法，则第一个参数是 this ,然后才是方法的参数
                        int methodParameterIndex = isStatic ? index : index - 1;
                        if (0 <= methodParameterIndex && methodParameterIndex < methodParameterCount) {
                            methodParametersNames[methodParameterIndex].setParaName(name);
                        }
                        super.visitLocalVariable(name, desc, signature, start, end, index);
                    }
                };
            }
        }, 0);
        return methodParametersNames;
    }

    private static boolean matchTypes(Type[] types, Class<?>[] parameterTypes) {
        if (types.length != parameterTypes.length) {
            return false;
        }
        for (int i = 0; i < types.length; i++) {
            if (!Type.getType(parameterTypes[i]).equals(types[i])) {
                return false;
            }
        }
        return true;
    }
}
