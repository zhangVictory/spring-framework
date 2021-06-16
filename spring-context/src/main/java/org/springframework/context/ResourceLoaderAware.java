/*
 * Copyright 2002-2021 the original author or authors.
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

package org.springframework.context;

import org.springframework.beans.factory.Aware;
import org.springframework.core.io.ResourceLoader;

/**
 * bean想要获取到ResourceLoader对象，需要实现这个接口，这是ApplicationContextAware
 * 的一个替代品
 *
 * 当您的应用程序对象必须访问其名称经过计算的各种文件资源时，通常需要ResourceLoader。
 * 一个好的策略是让对象使用DefaultResourceLoader，但仍然实现ResourceLoaderware，
 * 以便在ApplicationContext中运行时允许重写。
 * 有关示例，请参见ReloadableResourceBundleMessageSource
 *
 * 还可以为resourcepatternsolver接口检查传入的ResourceLoader，并相应地进行转换，
 * 以便将资源模式解析为资源对象数组。在ApplicationContext中运行时，这始终有效
 * （因为context接口扩展了ResourcePatternResolver接口）。
 * 使用PathMatchingResourcePatternResolver作为默认值；
 * 另请参见ResourcePatternUtils.getResourcePatternResolver方法
 *
 * 作为resourcepatternsolver依赖项的替代方法，考虑公开Resource[]数组类型的bean属性，
 * 该属性通过模式字符串填充，并在绑定时由bean工厂进行自动类型转换
 *
 * Interface to be implemented by any object that wishes to be notified of the
 * {@link ResourceLoader} (typically the ApplicationContext) that it runs in.
 * This is an alternative to a full {@link ApplicationContext} dependency via
 * the {@link org.springframework.context.ApplicationContextAware} interface.
 *
 * <p>Note that {@link org.springframework.core.io.Resource} dependencies can also
 * be exposed as bean properties of type {@code Resource} or {@code Resource[]},
 * populated via Strings with automatic type conversion by the bean factory. This
 * removes the need for implementing any callback interface just for the purpose
 * of accessing specific file resources.
 *
 * <p>You typically need a {@link ResourceLoader} when your application object has to
 * access a variety of file resources whose names are calculated. A good strategy is
 * to make the object use a {@link org.springframework.core.io.DefaultResourceLoader}
 * but still implement {@code ResourceLoaderAware} to allow for overriding when
 * running in an {@code ApplicationContext}. See
 * {@link org.springframework.context.support.ReloadableResourceBundleMessageSource}
 * for an example.
 *
 * <p>A passed-in {@code ResourceLoader} can also be checked for the
 * {@link org.springframework.core.io.support.ResourcePatternResolver} interface
 * and cast accordingly, in order to resolve resource patterns into arrays of
 * {@code Resource} objects. This will always work when running in an ApplicationContext
 * (since the context interface extends the ResourcePatternResolver interface). Use a
 * {@link org.springframework.core.io.support.PathMatchingResourcePatternResolver} as
 * default; see also the {@code ResourcePatternUtils.getResourcePatternResolver} method.
 *
 * <p>As an alternative to a {@code ResourcePatternResolver} dependency, consider
 * exposing bean properties of type {@code Resource[]} array, populated via pattern
 * Strings with automatic type conversion by the bean factory at binding time.
 *
 * @author Juergen Hoeller
 * @author Chris Beams
 * @since 10.03.2004
 * @see ApplicationContextAware
 * @see org.springframework.core.io.Resource
 * @see org.springframework.core.io.ResourceLoader
 * @see org.springframework.core.io.support.ResourcePatternResolver
 */
public interface ResourceLoaderAware extends Aware {

	/**
	 * 这个方法在
	 * Set the ResourceLoader that this object runs in.
	 * <p>This might be a ResourcePatternResolver, which can be checked
	 * through {@code instanceof ResourcePatternResolver}. See also the
	 * {@code ResourcePatternUtils.getResourcePatternResolver} method.
	 * <p>Invoked after population of normal bean properties but before an init callback
	 * like InitializingBean's {@code afterPropertiesSet} or a custom init-method.
	 * Invoked before ApplicationContextAware's {@code setApplicationContext}.
	 * @param resourceLoader the ResourceLoader object to be used by this object
	 * @see org.springframework.core.io.support.ResourcePatternResolver
	 * @see org.springframework.core.io.support.ResourcePatternUtils#getResourcePatternResolver
	 */
	void setResourceLoader(ResourceLoader resourceLoader);

}
