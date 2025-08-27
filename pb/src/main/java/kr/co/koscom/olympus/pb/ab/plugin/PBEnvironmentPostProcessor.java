package kr.co.koscom.olympus.pb.ab.plugin;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.salt.RandomSaltGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.lang.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PBEnvironmentPostProcessor implements EnvironmentPostProcessor {

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment env, SpringApplication app) {

        StandardPBEStringEncryptor enc = new StandardPBEStringEncryptor();
        enc.setSaltGenerator(new RandomSaltGenerator());
        enc.setAlgorithm("PBEWithMD5AndDES");
        enc.setStringOutputType("BASE64");
        enc.setPassword("KOSCOM");

        Pattern p1 = Pattern.compile("ENC\\(([^()]*)\\)");
        MutablePropertySources ms = env.getPropertySources();
        for (PropertySource p : ms) {
            if (p.getSource() instanceof Map m) {
                int[] i = {0};
                HashMap<Object, Object> map = new HashMap<>(m);
                for (Map.Entry<Object, Object> e : map.entrySet()) {
                    Matcher m1 = p1.matcher(e.getValue().toString());
                    if (m1.find()) {
                        String rv = m1.group(1);
                        String xv = enc.decrypt(rv);
                        e.setValue(xv);
                        i[0]++;
                    }
                }
                if (i[0] > 0) {
                    String n = p.getName();
                    PropertySource x = new PropertySource<Map<Object, Object>>(n, map) {
                        @Override
                        public Object getProperty(@NonNull String name) {
                            return source.get(name);
                        }
                    };
                    ms.replace(n, x);
                }
            }
        }
    }
}
