/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.ide.idea.language.tag;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReferenceProvider;
import com.intellij.psi.impl.source.resolve.reference.impl.providers.JavaClassReferenceProvider;
import com.intellij.psi.xml.XmlAttribute;
import com.intellij.psi.xml.XmlToken;
import com.intellij.psi.xml.XmlTokenType;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * @author Dominik Marks
 */
public class LiferayTaglibClassNameReferenceContributor extends AbstractLiferayTaglibReferenceContributor {

	@Override
	protected String[] getAttributeNames() {
		return new String[] {
			"assetCategoryClassName", "assetTagClassName", "className", "exception", "model", "modelResource",
			"portletProviderClassName"
		};
	}

	@Override
	protected PsiReferenceProvider getPsiReferenceProvider() {
		JavaClassReferenceProvider javaClassReferenceProvider = new JavaClassReferenceProvider();

		javaClassReferenceProvider.setOption(JavaClassReferenceProvider.ADVANCED_RESOLVE, Boolean.TRUE);
		javaClassReferenceProvider.setOption(JavaClassReferenceProvider.RESOLVE_QUALIFIED_CLASS_NAME, Boolean.TRUE);
		javaClassReferenceProvider.setOption(JavaClassReferenceProvider.ALLOW_DOLLAR_NAMES, Boolean.FALSE);

		return javaClassReferenceProvider;
	}

	@Override
	protected Map<String, Collection<AbstractMap.SimpleImmutableEntry<String, String>>> getTaglibAttributesMap() {
		return _taglibAttributes;
	}

	@Override
	protected boolean isSuitableXmlAttribute(XmlAttribute xmlAttribute) {
		if (_containsTextOnly(xmlAttribute)) {
			return super.isSuitableXmlAttribute(xmlAttribute);
		}

		return false;
	}

	private boolean _containsTextOnly(XmlAttribute xmlAttribute) {
		return Stream.of(
			xmlAttribute
		).map(
			XmlAttribute::getValueElement
		).filter(
			Objects::nonNull
		).map(
			PsiElement::getChildren
		).flatMap(
			Stream::of
		).filter(
			child -> child instanceof XmlToken
		).map(
			xmlToken -> (XmlToken)xmlToken
		).map(
			XmlToken::getTokenType
		).anyMatch(
			XmlTokenType.XML_ATTRIBUTE_VALUE_TOKEN::equals
		);
	}

	@SuppressWarnings("serial")
	private static Map<String, Collection<AbstractMap.SimpleImmutableEntry<String, String>>> _taglibAttributes =
		new HashMap<String, Collection<AbstractMap.SimpleImmutableEntry<String, String>>>() {
			{
				put(
					LiferayTaglibs.TAGLIB_URI_LIFERAY_ASSET,
					Arrays.asList(
						new AbstractMap.SimpleImmutableEntry<>("asset-categories-available", "className"),
						new AbstractMap.SimpleImmutableEntry<>("asset-categories-selector", "className"),
						new AbstractMap.SimpleImmutableEntry<>("asset-categories-summary", "className"),
						new AbstractMap.SimpleImmutableEntry<>("asset-display", "className"),
						new AbstractMap.SimpleImmutableEntry<>("asset-entry-usages", "className"),
						new AbstractMap.SimpleImmutableEntry<>("asset-links", "className"),
						new AbstractMap.SimpleImmutableEntry<>("asset-metadata", "className"),
						new AbstractMap.SimpleImmutableEntry<>("asset-tags-available", "className"),
						new AbstractMap.SimpleImmutableEntry<>("asset-tags-selector", "className"),
						new AbstractMap.SimpleImmutableEntry<>("asset-tags-summary", "className"),
						new AbstractMap.SimpleImmutableEntry<>("asset-view-usages", "className"),
						new AbstractMap.SimpleImmutableEntry<>("input-asset-links", "className")));

				put(
					LiferayTaglibs.TAGLIB_URI_LIFERAY_AUI,
					Arrays.asList(
						new AbstractMap.SimpleImmutableEntry<>("model-context", "model"),
						new AbstractMap.SimpleImmutableEntry<>("input", "model"),
						new AbstractMap.SimpleImmutableEntry<>("select", "model"),
						new AbstractMap.SimpleImmutableEntry<>("workflow-status", "model")));

				put(
					LiferayTaglibs.TAGLIB_URI_LIFERAY_COMMENT,
					Arrays.asList(new AbstractMap.SimpleImmutableEntry<>("discussion", "className")));

				put(
					LiferayTaglibs.TAGLIB_URI_LIFERAY_EXPANDO,
					Arrays.asList(
						new AbstractMap.SimpleImmutableEntry<>("custom-attribute", "className"),
						new AbstractMap.SimpleImmutableEntry<>("custom-attribute-list", "className"),
						new AbstractMap.SimpleImmutableEntry<>("custom-attributes-available", "className")));

				put(
					LiferayTaglibs.TAGLIB_URI_LIFERAY_EXPORT_IMPORT_CHANGESET,
					Arrays.asList(
						new AbstractMap.SimpleImmutableEntry<>("export-entity", "className"),
						new AbstractMap.SimpleImmutableEntry<>("publish-entity-menu-item", "className")));

				put(
					LiferayTaglibs.TAGLIB_URI_LIFERAY_FLAGS,
					Arrays.asList(new AbstractMap.SimpleImmutableEntry<>("flags", "className")));

				put(
					LiferayTaglibs.TAGLIB_URI_LIFERAY_PORTLET,
					Arrays.asList(new AbstractMap.SimpleImmutableEntry<>("runtime", "portletProviderClassName")));

				put(
					LiferayTaglibs.TAGLIB_URI_LIFERAY_SECURITY,
					Arrays.asList(new AbstractMap.SimpleImmutableEntry<>("permissionsURL", "modelResource")));

				put(
					LiferayTaglibs.TAGLIB_URI_LIFERAY_SHARING,
					Arrays.asList(new AbstractMap.SimpleImmutableEntry<>("button", "className")));

				put(
					LiferayTaglibs.TAGLIB_URI_LIFERAY_UI,
					Arrays.asList(
						new AbstractMap.SimpleImmutableEntry<>("app-view-entry", "assetCategoryClassName"),
						new AbstractMap.SimpleImmutableEntry<>("app-view-entry", "assetTagClassName"),
						new AbstractMap.SimpleImmutableEntry<>("asset-categories-available", "className"),
						new AbstractMap.SimpleImmutableEntry<>("asset-categories-selector", "className"),
						new AbstractMap.SimpleImmutableEntry<>("asset-categories-summary", "className"),
						new AbstractMap.SimpleImmutableEntry<>("asset-display", "className"),
						new AbstractMap.SimpleImmutableEntry<>("asset-links", "className"),
						new AbstractMap.SimpleImmutableEntry<>("asset-metadata", "className"),
						new AbstractMap.SimpleImmutableEntry<>("asset-tags-available", "className"),
						new AbstractMap.SimpleImmutableEntry<>("asset-tags-selector", "className"),
						new AbstractMap.SimpleImmutableEntry<>("asset-tags-summary", "className"),
						new AbstractMap.SimpleImmutableEntry<>("discussion", "className"),
						new AbstractMap.SimpleImmutableEntry<>("error", "exception"),
						new AbstractMap.SimpleImmutableEntry<>("input-asset-links", "className"),
						new AbstractMap.SimpleImmutableEntry<>("input-field", "model"),
						new AbstractMap.SimpleImmutableEntry<>("input-permissions", "modelName"),
						new AbstractMap.SimpleImmutableEntry<>("input-permissions-params", "modelName"),
						new AbstractMap.SimpleImmutableEntry<>("ratings", "className"),
						new AbstractMap.SimpleImmutableEntry<>("search-container-row", "className"),
						new AbstractMap.SimpleImmutableEntry<>("social-activities", "className")));
			}
		};

}