/*
 * Copyright 2002-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.core.env;

import org.springframework.lang.Nullable;

/**
 * {@link PropertyResolver} implementation that resolves property values against
 * an underlying set of {@link PropertySources}.
 *
 * @author Chris Beams
 * @author Juergen Hoeller
 * @since 3.1
 * @see PropertySource
 * @see PropertySources
 * @see AbstractEnvironment
 */
public class PropertySourcesPropertyResolver extends AbstractPropertyResolver {

	@Nullable
	private final PropertySources propertySources;


	/**
	 * Create a new resolver against the given property sources.
	 * @param propertySources the set of {@link PropertySource} objects to use
	 */
	public PropertySourcesPropertyResolver(@Nullable PropertySources propertySources) {
		this.propertySources = propertySources;
	}


	@Override
	public boolean containsProperty(String key) {
		if (this.propertySources != null) {
			for (PropertySource<?> propertySource : this.propertySources) {
				if (propertySource.containsProperty(key)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	@Nullable
	public String getProperty(String key) {
		return getProperty(key, String.class, true);
	}

	@Override
	@Nullable
	public <T> T getProperty(String key, Class<T> targetValueType) {
		return getProperty(key, targetValueType, true);
	}

	//实现了父类AbstractPropertyResolver的方法，根据名称获取属性值
	@Override
	@Nullable
	protected String getPropertyAsRawString(String key) {
		return getProperty(key, String.class, false);
	}

	/**
	 *
	 * @param key 属性名称
	 * @param targetValueType 目标值类型
	 * @param resolveNestedPlaceholders 是否解析内嵌的属性
	 * @param <T>
	 * @return
	 */
	@Nullable
	protected <T> T getProperty(String key, Class<T> targetValueType, boolean resolveNestedPlaceholders) {
		/**
		 * @see AbstractEnvironment#createPropertyResolver 在AbstractEnvironment的构造方法中初始化了一个PropertySources并传递给createPropertyResolver方法 它是MutablePropertySources
		 */
		if (this.propertySources != null) {
			for (PropertySource<?> propertySource : this.propertySources) {
				if (logger.isTraceEnabled()) {
					logger.trace("Searching for key '" + key + "' in PropertySource '" +
							propertySource.getName() + "'");
				}
				Object value = propertySource.getProperty(key);
				if (value != null) {
					if (resolveNestedPlaceholders && value instanceof String) {
						value = resolveNestedPlaceholders((String) value);
					}
					logKeyFound(key, propertySource, value);
					return convertValueIfNecessary(value, targetValueType);
				}
			}
		}
		if (logger.isTraceEnabled()) {
			logger.trace("Could not find key '" + key + "' in any property source");
		}
		return null;
	}

	/**
	 * Log the given key as found in the given {@link PropertySource}, resulting in
	 * the given value.
	 * <p>The default implementation writes a debug log message with key and source.
	 * As of 4.3.3, this does not log the value anymore in order to avoid accidental
	 * logging of sensitive settings. Subclasses may override this method to change
	 * the log level and/or log message, including the property's value if desired.
	 * @param key the key found
	 * @param propertySource the {@code PropertySource} that the key has been found in
	 * @param value the corresponding value
	 * @since 4.3.1
	 */
	protected void logKeyFound(String key, PropertySource<?> propertySource, Object value) {
		if (logger.isDebugEnabled()) {
			logger.debug("Found key '" + key + "' in PropertySource '" + propertySource.getName() +
					"' with value of type " + value.getClass().getSimpleName());
		}
	}

}
