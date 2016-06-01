/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.processor;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathFunctionResolver;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangeProperty;
import org.apache.camel.builder.xml.DefaultNamespaceContext;
import org.apache.camel.builder.xml.XPathBuilder;
import org.apache.camel.component.xmlsecurity.api.XmlSignatureException;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.apache.xml.utils.PrefixResolverDefault;
import org.apache.xpath.jaxp.JAXPExtensionsProvider;
import org.apache.xpath.jaxp.JAXPPrefixResolver;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class BodyInAggregatingStrategy implements AggregationStrategy {

    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
	if (oldExchange == null) {
	    return newExchange;
	}

	String oldBody = oldExchange.getIn().getBody(String.class);
	String newBody = newExchange.getIn().getBody(String.class);
	//oldExchange.getIn().setBody(oldBody + "+" + newBody);
	oldExchange.getIn().setBody(oldBody + newBody);
	return oldExchange;
    }

    /**
     * An expression used to determine if the aggregation is complete
     */
    public boolean isCompleted(@ExchangeProperty(Exchange.AGGREGATED_SIZE) Integer aggregated) {
	if (aggregated == null) {
	    return false;
	}

	return aggregated == 3;
    }

    public static void main(String[] args) throws IOException, ParserConfigurationException, XmlSignatureException, SAXException,
	    XPathExpressionException, TransformerException {
	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	dbf.setNamespaceAware(true);
	dbf.setValidating(false);
	// avoid external entity attacks
	dbf.setFeature("http://xml.org/sax/features/external-general-entities", false);
	dbf.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
	boolean isDissalowDoctypeDecl = true;
	dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", isDissalowDoctypeDecl);
	// avoid overflow attacks
	dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
	Node node = dbf
		.newDocumentBuilder()
		.parse(new FileInputStream(
			"/home/leonardocarvalho/desenvolvimento/projeto-sepel2/camel/src/main/java/org/apache/camel/processor/texto.txt"))
		.getDocumentElement();

	XPathFactory factory = XPathFactory.newInstance();
	XPath xpath = factory.newXPath();
	xpath.setNamespaceContext(new XPathNamespaceContext(new HashMap<String, String>()));
	xpath.compile("eSocial/evtAdmissao/@id");

//	JAXPExtensionsProvider jep = new JAXPExtensionsProvider((XPathFunctionResolver) new XPathBuilder("eSocial/evtAdmissao/@id"), false);
//	org.apache.xpath.XPathContext xpathSupport = new org.apache.xpath.XPathContext(jep, false);
//
//	org.apache.xpath.XPath xpath2 = new org.apache.xpath.XPath("eSocial/evtAdmissao/@id", null, new JAXPPrefixResolver(
//		new DefaultNamespaceContext()), org.apache.xpath.XPath.SELECT);
//
//	xpath2.execute(xpathSupport, node, new JAXPPrefixResolver(new DefaultNamespaceContext()));
    }

    private static class XPathNamespaceContext implements NamespaceContext {

	private final Map<String, String> prefix2Namespace;

	XPathNamespaceContext(Map<String, String> prefix2Namespace) {
	    this.prefix2Namespace = prefix2Namespace;
	}

	public String getNamespaceURI(String prefix) {
	    if (prefix == null) {
		throw new NullPointerException("Null prefix");
	    }
	    if ("xml".equals(prefix)) {
		return XMLConstants.XML_NS_URI;
	    }
	    String ns = prefix2Namespace.get(prefix);
	    if (ns != null) {
		return ns;
	    }
	    return XMLConstants.NULL_NS_URI;
	}

	// This method isn't necessary for XPath processing.
	public String getPrefix(String uri) {
	    throw new UnsupportedOperationException();
	}

	// This method isn't necessary for XPath processing either.
	@SuppressWarnings("rawtypes")
	public Iterator getPrefixes(String uri) {
	    throw new UnsupportedOperationException();
	}

    }

}