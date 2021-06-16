/*
 * Copyright 2002-2019 the original author or authors.
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

package org.springframework.beans.factory.config;

import org.springframework.beans.BeansException;

/**
 * 工厂钩子，允许自定义修改应用程序上下文的bean定义，调整上下文基础bean工厂的bean属性值。
 *
 * 对于覆盖在应用程序上下文中配置的bean属性的针对系统管理员的自定义配置文件非常有用。请参阅PropertyResourceConfigurer及其具体实现，以获取满足此类配置需求的现成解决方案。
 *
 * BeanFactoryPostProcessor可以与bean定义进行交互和修改，但不能与bean实例进行交互。这样做可能会导致bean过早实例化，违反容器并导致意外的副作用。如果需要bean实例交互，请考虑改为实现BeanPostProcessor。
 *
 * ApplicationContext自动在其bean定义中检测BeanFactoryPostProcessor bean，并在创建任何其他bean之前应用它们。BeanFactoryPostProcessor也可以用ConfigurableApplicationContext以编程方式注册。
 *
 * 在ApplicationContext中自动检测到的BeanFactoryPostProcessor bean将根据PriorityOrdered和ordered语义进行排序。相反，用ConfigurableApplicationContext编程注册的BeanFactoryPostProcessorbean将按注册顺序应用；对于以编程方式注册的后处理器，通过实现PriorityOrdered或Ordered接口表达的任何排序语义都将被忽略。此外，beanfactorypostprocessorbean不考虑@Order注释。
 *
 * Factory hook that allows for custom modification of an application context's
 * bean definitions, adapting the bean property values of the context's underlying
 * bean factory.
 *
 * <p>Useful for custom config files targeted at system administrators that
 * override bean properties configured in the application context. See
 * {@link PropertyResourceConfigurer} and its concrete implementations for
 * out-of-the-box solutions that address such configuration needs.
 *
 * <p>A {@code BeanFactoryPostProcessor} may interact with and modify bean
 * definitions, but never bean instances. Doing so may cause premature bean
 * instantiation, violating the container and causing unintended side-effects.
 * If bean instance interaction is required, consider implementing
 * {@link BeanPostProcessor} instead.
 *
 * <h3>Registration</h3>
 * <p>An {@code ApplicationContext} auto-detects {@code BeanFactoryPostProcessor}
 * beans in its bean definitions and applies them before any other beans get created.
 * A {@code BeanFactoryPostProcessor} may also be registered programmatically
 * with a {@code ConfigurableApplicationContext}.
 *
 * <h3>Ordering</h3>
 * <p>{@code BeanFactoryPostProcessor} beans that are autodetected in an
 * {@code ApplicationContext} will be ordered according to
 * {@link org.springframework.core.PriorityOrdered} and
 * {@link org.springframework.core.Ordered} semantics. In contrast,
 * {@code BeanFactoryPostProcessor} beans that are registered programmatically
 * with a {@code ConfigurableApplicationContext} will be applied in the order of
 * registration; any ordering semantics expressed through implementing the
 * {@code PriorityOrdered} or {@code Ordered} interface will be ignored for
 * programmatically registered post-processors. Furthermore, the
 * {@link org.springframework.core.annotation.Order @Order} annotation is not
 * taken into account for {@code BeanFactoryPostProcessor} beans.
 *
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @since 06.07.2003
 * @see BeanPostProcessor
 * @see PropertyResourceConfigurer
 */
@FunctionalInterface
public interface BeanFactoryPostProcessor {

	/**
	 * 在标准初始化之后修改应用程序上下文的内部bean工厂。所有bean定义都将被加载，但是还没有bean被实例化。这允许重写或添加属性，即使是对bean
	 *
	 * Modify the application context's internal bean factory after its standard
	 * initialization. All bean definitions will have been loaded, but no beans
	 * will have been instantiated yet. This allows for overriding or adding
	 * properties even to eager-initializing beans.
	 * @param beanFactory the bean factory used by the application context
	 * @throws org.springframework.beans.BeansException in case of errors
	 */
	void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;

}
