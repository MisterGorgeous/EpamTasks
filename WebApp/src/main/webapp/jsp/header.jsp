
    <nav class="navbar navbar-default">
        <div class="container-fluid navbar-inverse">

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#">Logo <span class="sr-only">(current)</span></a></li>

                    <form class="navbar-form navbar-left">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="Search">
                        </div>
                        <button type="submit" class="btn btn-default">Submit</button>
                    </form>


                    <form class="navbar-form navbar-left" name="locale" action="Controller" method="post">
                        <input type="hidden" name="command" value="cross">
                        <input type="hidden" name="page" value="path.page.signin">
                        <input type="submit" name="button" class="btn btn-default"
                               value="<fmt:message key="main.sigin" bundle="${resourceBundle}"/>"/> />
                    </form>


                    <form class="navbar-form navbar-left" name="locale" action="Controller" method="post">
                        <input type="hidden" name="command" value="cross">
                        <input type="hidden" name="page" value="path.page.login">
                        <input type="submit" name="button" class="btn btn-default"
                               value="<fmt:message key="main.login" bundle="${resourceBundle}"/>"/> />
                    </form>

                    <form class="navbar-form navbar-left" name="locale" action="Controller" method="post">
                        <input type="hidden" name="command" value="locale">
                        <input type="hidden" name="page" value="path.page.main">
                        <input type="submit" name="button" class="btn btn-default"
                               value="<fmt:message key="main.en" bundle="${resourceBundle}"/>"/>
                        <input type="submit" name="button" class="btn btn-default"
                               value="<fmt:message key="main.ru" bundle="${resourceBundle}"/>"/>
                    </form>

                </ul>

                <ul class="nav navbar-nav navbar-right">

                    <c:if test="${userStatus == 'USER'}">

                            <form class="navbar-form navbar-right" title="logoof" action="Controller" method="post">
                                <input type="hidden" name="command" value="logoff">
                                <input type="hidden" name="page" value="path.page.main">
                                <input class="btn btn-default" type="submit" title="button"
                                       value="<fmt:message key="header.logoff" bundle="${resourceBundle}"/>"/> />
                            </form>

                      <%--  <form class="navbar-form navbar-right" title="signin" action="Controller" method="post">
                            <input type="hidden" name="command" value="cross">
                            <input type="hidden" name="page" value="path.page.signin">
                            <!--<input class="btn btn-default" type="submit"  title="button" value="user" /> />-->
                            <button type="submit" class="btn btn-default">
                                <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                                    ${userName}
                            </button>
                        </form>--%>
                    </c:if>


                    <c:if test="${userStatus == 'ADMINISTRATOR'}">

                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Actions <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li> <form action="Controller" method="post">
                                    <input type="hidden" name="command" value="users">
                                    <input type="hidden" name="page" value="path.page.admin">
                                    <input class="btn btn-default" type="submit" title="button"
                                           value="<fmt:message key="header.users" bundle="${resourceBundle}"/>"/> />
                                </form>
                                </li>
                                <li role="separator" class="divider"></li>
                                <li> <form action="Controller" method="post">
                                    <input type="hidden" name="command" value="addfilm">
                                    <input type="hidden" name="page" value="path.page.admin">
                                    <input class="btn btn-default" type="submit" title="button"
                                           value="<fmt:message key="header.addfilm" bundle="${resourceBundle}"/>"/> />
                                </form>
                                </li>
                            </ul>
                        </li>

                            <form class="navbar-form navbar-right" title="logoof" action="Controller" method="post">
                                <input type="hidden" name="command" value="logoff">
                                <input type="hidden" name="page" value="path.page.main">
                                <input class="btn btn-default" type="submit" title="button"
                                       value="<fmt:message key="header.logoff" bundle="${resourceBundle}"/>"/> />
                            </form>

                        <form class="navbar-form navbar-right"  action="Controller" method="post">
                            <input type="hidden" name="command" value="cross">
                            <input type="hidden" name="page" value="path.page.admin">
                            <!--<input class="btn btn-default" type="submit"  title="button" value="user" /> />-->
                            <button type="submit" class="btn btn-default">
                                <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                                Admin
                            </button>
                        </form>
                    </c:if>
                </ul>

            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>


</header>