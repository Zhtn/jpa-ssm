<%--
  Created by IntelliJ IDEA.
  User: Z
  Date: 2019/9/27
  Time: 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- ========== Left Sidebar Start ========== -->
<div class="left side-menu">
    <div class="slimscroll-menu" id="remove-scroll">

        <!-- User box -->
        <div class="user-box" align="center">
            <div class="user-img">
                <img src="${pageContext.request.contextPath}/assets/images/users/lxi.jpg" alt="user-img"
                     title="Mat Helme" class="rounded-circle img-fluid">
            </div>
            <h5>${info.user.name}</h5>
            <ul class="list-inline">
                <li class="list-inline-item">
                    <a href="javascript:void(0);" class="waves-effect">
                        <i class="fi-cog"></i>
                    </a>
                </li>

                <li class="list-inline-item">
                    <a href="${pageContext.request.contextPath }/system/logout.do" class="text-custom">
                        <i class="fi-power"></i>
                    </a>
                </li>
            </ul>
        </div>
        <!--- Sidemenu -->
        <div id="sidebar-menu">
            <ul class="metismenu" id="side-menu">
                <li>
                    <a href="${pageContext.request.contextPath}/system/index.do">
                        <i class="fi-air-play"></i><span class="badge badge-danger badge-pill pull-right"></span> <span>主页</span>
                    </a>
                </li>


                <c:forEach items="${info.menus}" var="menu">
                    <li>
                        <c:if test="${empty menu.children}">
                            <a href="${pageContext.request.contextPath}${menu.path}">${menu.name}</a>
                        </c:if>

                        <c:if test="${not empty menu.children}">
                            <a href="javascript:void(0);">
                                <i class="icon-menu"></i><span>${menu.name}</span>
                            </a>
                        </c:if>

                        <ul class="nav-second-level" aria-expanded="false">

                            <c:forEach items="${menu.children}" var="child">
                                <li>
                                    <c:if test="${empty child.children}">
                                        <c:if test="${child.generateMenu == 1}">
                                            <a href="${pageContext.request.contextPath}${child.path}">${child.name}</a>
                                        </c:if>
                                    </c:if>
                                </li>
                                <c:if test="${ not empty child.children}">

                                    <a href="javascript:void(0);">
                                        <span><strong>${child.name}</strong></span>
                                    </a>
                                    <ul>
                                        <c:forEach items="${child.children}" var="gchild">
                                            <c:if test="${gchild.generateMenu == 1}">
                                                <li>
                                                    <a href="${pageContext.request.contextPath}${gchild.path}">${gchild.name}</a>
                                                </li>
                                            </c:if>
                                        </c:forEach>
                                    </ul>
                                </c:if>
                                </li>
                            </c:forEach>
                        </ul>
                    </li>
                </c:forEach>
            </ul>

            <div class="clearfix"></div>
        </div>
        <!-- Sidebar -->
        <div class="clearfix"></div>
    </div>
</div>
<!-- Left Sidebar End -->
