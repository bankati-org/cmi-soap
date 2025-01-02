package com.bankati.cmi.config;


import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }


    @Bean
    public XsdSchema accountSchema() {
        return new SimpleXsdSchema(new ClassPathResource("xsd/accounts.xsd"));
    }

    @Bean
    public XsdSchema transactionSchema() {
        return new SimpleXsdSchema(new ClassPathResource("xsd/transactions.xsd"));
    }

    @Bean
    public XsdSchema paymentSchema() {
        return new SimpleXsdSchema(new ClassPathResource("xsd/payments.xsd"));
    }

    @Bean(name = "payments")
    public DefaultWsdl11Definition paymentsWsdl(XsdSchema paymentSchema) {
        DefaultWsdl11Definition wsdl = new DefaultWsdl11Definition();
        wsdl.setPortTypeName("PaymentsPort");
        wsdl.setLocationUri("/ws/payments");
        wsdl.setTargetNamespace("http://bankati.com/cmi/payment");
        wsdl.setSchema(paymentSchema);
        return wsdl;
    }
    // Définitions WSDL pour chaque service


    @Bean(name = "transactions")
    public DefaultWsdl11Definition transactionsWsdl(XsdSchema transactionSchema) {
        DefaultWsdl11Definition wsdl = new DefaultWsdl11Definition();
        wsdl.setPortTypeName("TransactionsPort");
        wsdl.setLocationUri("/ws/transactions");
        wsdl.setTargetNamespace("http://bankati.com/cmi/transaction");
        wsdl.setSchema(transactionSchema);
        return wsdl;
    }

    @Bean(name = "accounts")
    public DefaultWsdl11Definition accountsWsdl(XsdSchema accountSchema) {
        DefaultWsdl11Definition wsdl = new DefaultWsdl11Definition();
        wsdl.setPortTypeName("AccountsPort");
        wsdl.setLocationUri("/ws/accounts");
        wsdl.setTargetNamespace("http://www.bankati.com/cmi/account");
        wsdl.setSchema(accountSchema);
        return wsdl;
    }

}