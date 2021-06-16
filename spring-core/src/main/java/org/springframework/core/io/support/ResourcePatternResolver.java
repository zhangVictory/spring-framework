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

package org.springframework.core.io.support;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

/**
 *
 * 用于将位置模式（例如，Ant样式的路径模式）解析为资源对象的策略接口。
 *
 * 这是ResourceLoader接口的扩展。可以检查传入的ResourceLoader（例如，
 * 在上下文中运行时通过ResourceLoaderware传入的ApplicationContext）
 * 是否也实现了此扩展接口。
 *
 * PathMatchingResourcePatternResolver是一个独立的实现，可在
 * ApplicationContext外部使用，ResourceArrayPropertyEditor
 * 也可用于填充资源数组bean属性。
 *
 * 可用于任何类型的位置模式（例如“/WEB-INF/*-context.xml”）：
 * 输入模式必须与策略实现相匹配。这个接口只是指定转换方法，而不是
 * 指定特定的模式格式。
 *
 * 此接口还为类路径中的所有匹配资源建议一个新的资源前缀“classpath*：”。
 * 注意，在这种情况下，资源位置应该是一个没有占位符的路径（例如“/beans.xml”）
 * ；JAR文件或类路径中的不同目录可以包含多个同名文件。
 *
 *
 * Strategy interface for resolving a location pattern (for example,
 * an Ant-style path pattern) into {@link Resource} objects.
 *
 * <p>This is an extension to the {@link org.springframework.core.io.ResourceLoader}
 * interface. A passed-in {@code ResourceLoader} (for example, an
 * {@link org.springframework.context.ApplicationContext} passed in via
 * {@link org.springframework.context.ResourceLoaderAware} when running in a context)
 * can be checked whether it implements this extended interface too.
 *
 * <p>{@link PathMatchingResourcePatternResolver} is a standalone implementation
 * that is usable outside an {@code ApplicationContext}, also used by
 * {@link ResourceArrayPropertyEditor} for populating {@code Resource} array bean
 * properties.
 *
 * <p>Can be used with any sort of location pattern (e.g. "/WEB-INF/*-context.xml"):
 * Input patterns have to match the strategy implementation. This interface just
 * specifies the conversion method rather than a specific pattern format.
 *
 * <p>This interface also suggests a new resource prefix "classpath*:" for all
 * matching resources from the class path. Note that the resource location is
 * expected to be a path without placeholders in this case (e.g. "/beans.xml");
 * JAR files or different directories in the class path can contain multiple files
 * of the same name.
 *
 * @author Juergen Hoeller
 * @since 1.0.2
 * @see org.springframework.core.io.Resource
 * @see org.springframework.core.io.ResourceLoader
 * @see org.springframework.context.ApplicationContext
 * @see org.springframework.context.ResourceLoaderAware
 */
public interface ResourcePatternResolver extends ResourceLoader {

	/**
	 * 类路径中所有匹配资源的伪URL前缀：“classpath*：”
	 *
	 * 这与ResourceLoader的classpath URL前缀不同，
	 * 因为它检索给定名称（例如“/beans.xml”）的所有
	 * 匹配资源，例如在所有部署的JAR文件的根目录中。
	 *
	 *
	 * Pseudo URL prefix for all matching resources from the class path: "classpath*:"
	 * <p>This differs from ResourceLoader's classpath URL prefix in that it
	 * retrieves all matching resources for a given name (e.g. "/beans.xml"),
	 * for example in the root of all deployed JAR files.
	 * @see org.springframework.core.io.ResourceLoader#CLASSPATH_URL_PREFIX
	 */
	String CLASSPATH_ALL_URL_PREFIX = "classpath*:";

	/**
	 * 将给定的位置模式解析为资源对象。
	 *
	 * 应尽可能避免指向同一物理资源的重叠资源条目。
	 * 结果应该具有set语义
	 *
	 * Resolve the given location pattern into {@code Resource} objects.
	 * <p>Overlapping resource entries that point to the same physical
	 * resource should be avoided, as far as possible. The result should
	 * have set semantics.
	 * @param locationPattern the location pattern to resolve
	 * @return the corresponding {@code Resource} objects
	 * @throws IOException in case of I/O errors
	 */
	Resource[] getResources(String locationPattern) throws IOException;

}
