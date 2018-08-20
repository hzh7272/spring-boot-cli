package com.base.configuration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

/**
 * 消息转换器配置
 * @author hzh 2018/8/19 下午3:05
 */
@Configuration
public class MessageConvertersConfiguration {

	@Bean
	public ObjectMapper ss(Jackson2ObjectMapperBuilder builder) {
		var objectMapper = builder.createXmlMapper(false).build();
		objectMapper.setSerializerFactory(objectMapper.getSerializerFactory().withSerializerModifier(new MyBeanSerializerModifier()));
		return objectMapper;
	}

	/**
	 * 自定义Bean序列化修饰器
	 * @author hzh
	 */
	class MyBeanSerializerModifier extends BeanSerializerModifier {

		private MyNullArrayJsonSerializer myNullArrayJsonSerializer = new MyNullArrayJsonSerializer();
		private MyNullStringJsonSerializer myNullStringJsonSerializer = new MyNullStringJsonSerializer();
		private MyNullNumberJsonSerializer myNullNumberJsonSerializer = new MyNullNumberJsonSerializer();

		/**
		 * 集合类型断言
 		 */
		Predicate<BeanPropertyWriter> isArrayType = beanPropertyWriter -> {
			var rawClass = beanPropertyWriter.getType().getRawClass();
			return rawClass.equals(List.class) || rawClass.equals(Set.class);
		};

		/**
		 * 数字类型断言
		 */
		Predicate<BeanPropertyWriter> isNumberType = beanPropertyWriter -> {
			var rawClass = beanPropertyWriter.getType().getRawClass();
			return rawClass.equals(Integer.class) || rawClass.equals(Double.class) || rawClass.equals(Long.class);
		};

		@Override
		public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc, List<BeanPropertyWriter> beanProperties) {
			beanProperties.forEach(beanPropertyWriter -> {
				if (isArrayType.test(beanPropertyWriter)) {
					beanPropertyWriter.assignNullSerializer(this.myNullArrayJsonSerializer);
				} else if (isNumberType.test(beanPropertyWriter)) {
					beanPropertyWriter.assignNullSerializer(this.myNullNumberJsonSerializer);
				} else {
					beanPropertyWriter.assignNullSerializer(this.myNullStringJsonSerializer);
				}
			});
			return beanProperties;
		}
	}

	/**
	 * 自定义集合json Serializer
	 * @author hzh
	 */
	class MyNullArrayJsonSerializer extends JsonSerializer<Object> {
		@Override
		public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
			if (null == o) {
				jsonGenerator.writeStartArray();
				jsonGenerator.writeEndArray();
			} else {
				jsonGenerator.writeObject(o);
			}
		}
	}

	/**
	 * 自定义字符串json Serializer
	 * @author hzh
	 */
	class MyNullStringJsonSerializer extends JsonSerializer<Object> {
		@Override
		public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
			if (null == o) {
				jsonGenerator.writeString("");
			} else {
				jsonGenerator.writeObject(o);
			}
		}
	}

	/**
	 * 自定义数字类型json Serializer
	 * @author hzh
	 */
	class MyNullNumberJsonSerializer extends JsonSerializer<Object> {
		@Override
		public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
			if (null == o) {
				jsonGenerator.writeNumber(0);
			} else {
				jsonGenerator.writeObject(o);
			}
		}
	}
}
