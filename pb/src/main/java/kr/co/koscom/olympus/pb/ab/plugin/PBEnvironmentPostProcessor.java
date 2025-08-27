
package kr.co.koscom.olympus.pb.ab.plugin;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.salt.RandomSaltGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.lang.NonNull;

public class PBEnvironmentPostProcessor implements EnvironmentPostProcessor {

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment env, SpringApplication app) {

		StandardPBEStringEncryptor enc = new StandardPBEStringEncryptor();
		enc.setSaltGenerator(new RandomSaltGenerator());
		enc.setAlgorithm("PBEWithMD5AndDES");
		enc.setStringOutputType("BASE64");
		enc.setPassword("KOSCOM");

		Map map = new HashMap<>();

		Pattern p1 = Pattern.compile("ENC\\(([^()]*)\\)");
		MutablePropertySources ms = env.getPropertySources();

		for (PropertySource p : ms)
			if (p.getSource() instanceof Map m)
				m.forEach((k, v) -> {
					Matcher t = p1.matcher(v.toString());
					if (t.find()) {
						String rv = t.group(1);
						String xv = enc.decrypt(rv);
						map.put(k, xv);
					}
				});

		if (!map.isEmpty()) {
			PropertySource p = new PropertySource<Map<Object, Object>>("PB_ENC", map) {
				@Override
				public Object getProperty(@NonNull String name) {
					return source.get(name);
				}
			};
			ms.addFirst(p);
		}
    }
}
