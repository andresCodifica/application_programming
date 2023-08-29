package com.api.bank.infrastructure.controller.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public final class Utils {


    public static ObjectMapper getObjectMapper() {
        return getObjectMapper((ObjectMapper)null);
    }

    private static ObjectMapper getObjectMapper(ObjectMapper objectMapper) {
        return objectMapper == null ? (new ObjectMapper()).registerModule(new JavaTimeModule()).findAndRegisterModules().configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false) : objectMapper;
    }

    public static Object parseBase64StringToObject(String pObjectAsString) {
        byte[] data = Base64.getDecoder().decode(pObjectAsString);
        Object parsedObject = null;

        try {
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
            Throwable var4 = null;

            try {
                parsedObject = ois.readObject();
            } catch (Throwable var14) {
                var4 = var14;
                throw var14;
            } finally {
                if (ois != null) {
                    if (var4 != null) {
                        try {
                            ois.close();
                        } catch (Throwable var13) {
                            var4.addSuppressed(var13);
                        }
                    } else {
                        ois.close();
                    }
                }

            }
        } catch (ClassNotFoundException | IOException var16) {
            log.error("Error in fromString: ", var16);
        }

        return parsedObject;
    }



    public static String stringifyAsJson(Object params) {
        try {
            return getObjectMapper().writeValueAsString(params);
        } catch (Exception var2) {
            log.error(var2.getMessage(), var2);
            return null;
        }
    }

    public static String stringifyAsJson(Object... params) {
        Map<String, Object> paramsMap = new HashMap();

        for(int i = 0; i < params.length; i += 2) {
            if (i + 1 < params.length) {
                paramsMap.put(String.valueOf(params[i]), params[i + 1]);
            } else {
                paramsMap.put(String.valueOf(params[i]), (Object)null);
            }
        }

        try {
            return getObjectMapper().writeValueAsString(paramsMap);
        } catch (Exception var3) {
            log.error(var3.getMessage(), var3);
            return null;
        }
    }

    public static String stringifyAsJson(ObjectMapper objectMapper, Object params) {
        try {
            return getObjectMapper(objectMapper).writeValueAsString(params);
        } catch (Exception var3) {
            log.error(var3.getMessage(), var3);
            return null;
        }
    }

    public static String stringifyAsJson(ObjectMapper objectMapper, Object... params) {
        Map<String, Object> paramsMap = new HashMap();

        for(int i = 0; i < params.length; i += 2) {
            if (i + 1 < params.length) {
                paramsMap.put(String.valueOf(params[i]), params[i + 1]);
            } else {
                paramsMap.put(String.valueOf(params[i]), (Object)null);
            }
        }

        try {
            return getObjectMapper(objectMapper).writeValueAsString(paramsMap);
        } catch (Exception var4) {
            log.error(var4.getMessage(), var4);
            return null;
        }
    }

    public static String writeObjectToBase64String(Serializable opSerializableObject) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String objectAsString = null;

        try {
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            Throwable var4 = null;

            try {
                oos.writeObject(opSerializableObject);
                objectAsString = Base64.getEncoder().encodeToString(baos.toByteArray());
            } catch (Throwable var14) {
                var4 = var14;
                throw var14;
            } finally {
                if (oos != null) {
                    if (var4 != null) {
                        try {
                            oos.close();
                        } catch (Throwable var13) {
                            var4.addSuppressed(var13);
                        }
                    } else {
                        oos.close();
                    }
                }

            }
        } catch (IOException var16) {
            log.error("Error in toString: ", var16);
        }

        return objectAsString;
    }

    private Utils() {
        throw new UnsupportedOperationException();
    }
}
