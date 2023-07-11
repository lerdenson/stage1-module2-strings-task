package com.epam.mjc;

import java.util.ArrayList;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     * 1. access modifier - optional, followed by space: ' '
     * 2. return type - followed by space: ' '
     * 3. method name
     * 4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     * accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     * private void log(String value)
     * Vector3 distort(int x, int y, int z, float magnitude)
     * public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        String modifier = null;
        String type;
        String name;

        String[] functionOrParams = signatureString.split("\\(", 2);
        String[] mtn = functionOrParams[0].split(" ");
        if (mtn.length == 3) {
            modifier = mtn[0].trim();
            type = mtn[1].trim();
            name = mtn[2].trim();
        } else {
            type = mtn[0];
            name = mtn[1];
        }

        String[] StringParams = functionOrParams[1].trim().substring(0, functionOrParams[1].length() - 1).split(",");

        ArrayList<MethodSignature.Argument> arguments = new ArrayList<>();

        for (String p : StringParams) {
            String[] ParamTypeAndName = p.trim().split(" ", 2);
            if (ParamTypeAndName.length == 2)
                arguments.add(new MethodSignature.Argument(ParamTypeAndName[0], ParamTypeAndName[1]));
        }

        MethodSignature signature;

        if (arguments.size() > 0) signature = new MethodSignature(name, arguments);
        else signature = new MethodSignature(name);

        signature.setAccessModifier(modifier);
        signature.setReturnType(type);

        return signature;
    }
}
