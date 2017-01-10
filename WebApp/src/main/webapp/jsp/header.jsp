<nav class="navbar navbar-default">
    <div class="container-fluid ">

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <form class="navbar-form navbar-left" action="Controller" method="post">
                    <input type="hidden" name="command" value="setmaincontent">
                    <input type="hidden" name="page" value="path.page.main">
                    <button type="submit" class="btn btn-default"><img src="/img/logo.jpg" alt="" width="40" height="20"></button>

                </form>


                <form class="navbar-form navbar-left" name="search" action="Controller" method="post">
                    <input type="hidden" name="command" value="searchmovies">
                    <input type="hidden" name="page" value="path.page.main">
                    <div class="form-group">
                        <input name="searchMovie" type="text" placeholder="Search">
                    </div>
                    <button type="submit" class="btn btn-primary">Search</button>
                </form>


                <form class="navbar-form navbar-left" name="locale" action="Controller" method="post">
                    <input type="hidden" name="command" value="locale">
                    <input type="hidden" name="page" value="path.page.main">
                    <input type="submit" name="button" class="btn btn-primary"
                           value="<fmt:message key="main.en" bundle="${resourceBundle}"/>"/>
                    <input type="submit" name="button" class="btn btn-primary"
                           value="<fmt:message key="main.ru" bundle="${resourceBundle}"/>"/>
                </form>


                <form class="navbar-form navbar-left" name="signin" action="Controller" method="post">
                    <input type="hidden" name="command" value="">
                    <input type="hidden" name="page" value="path.page.signin">
                    <input type="submit" name="button" class="btn btn-primary"
                           value="<fmt:message key="main.sigin" bundle="${resourceBundle}"/>"/>
                </form>


                <form class="navbar-form navbar-left">
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
                        <fmt:message key="main.login" bundle="${resourceBundle}"/>
                    </button>
                </form>

                <!-- Modal -->
                <div class="modal fade  pagination-centered text-center" id="myModal" tabindex="-1" role="dialog"
                     aria-labelledby="myModalLabel"
                     aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content ">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                                <h4 class="modal-title" id="myModalLabel">Sign In</h4>
                            </div>
                            <div class="modal-body">
                                <div class="container">
                                    <div class="row">
                                        <div class="col-sm-6 col-md-4 col-lg-4">
                                            <div class="account-wall">
                                                <img class="profile-img"
                                                     src="/img/photo.png"
                                                     alt="">
                                                <form class="form-signin" title="login" action="Controller"
                                                      method="post">
                                                    <input class="form-control" type="hidden" name="command"
                                                           value="login">
                                                    <input class="form-control" type="hidden" name="page"
                                                           value="path.page.main">
                                                    <input name="login" type="text" class="form-control col-md-8"
                                                           placeholder="Login" pattern="[A-Za-z]\w{4,}" required
                                                           autofocus>
                                                    <input type="password" class="form-control" placeholder="Password"
                                                           name="pass"
                                                           pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{6,}$"
                                                           required>
                                                    <button id="submit" class="btn btn-lg btn-primary btn-block"
                                                            type="submit">Log on
                                                    </button>
                                                </form>

                                            </div>

                                        </div>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>


            </ul>


            <ul class="nav navbar-nav navbar-right">

                <c:if test="${userStatus != 'GUEST'}">

                    <c:if test="${userStatus == 'ADMINISTRATOR'}">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                               aria-haspopup="true"
                               aria-expanded="false">Actions <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li>
                                    <form action="Controller" method="post">
                                        <input type="hidden" name="command" value="users">
                                        <input type="hidden" name="page" value="path.page.admin">
                                        <input class="btn btn-primary" type="submit" title="button"
                                               value="<fmt:message key="header.users" bundle="${resourceBundle}"/>"/>
                                    </form>
                                </li>
                                <li role="separator" class="divider"></li>
                                <li>
                                    <form action="Controller" method="post">
                                        <input type="hidden" name="command" value="allgenres">
                                        <input type="hidden" name="page" value="path.page.addmovie">
                                        <input class="btn btn-primary" type="submit" title="button"
                                               value="<fmt:message key="header.addmovie" bundle="${resourceBundle}"/>"/>
                                    </form>
                                </li>
                                <li role="separator" class="divider"></li>
                                <li>
                                    <form action="Controller" method="post">
                                        <input type="hidden" name="command" value="">
                                        <input type="hidden" name="page" value="path.page.addactor">
                                        <input class="btn btn-primary" type="submit" title="button"
                                               value="<fmt:message key="header.addactor" bundle="${resourceBundle}"/>"/>
                                    </form>
                                </li>
                            </ul>
                        </li>
                    </c:if>

                    <form class="navbar-form navbar-right" title="logoof" action="Controller" method="post">
                        <input type="hidden" name="command" value="logoff">
                        <input type="hidden" name="page" value="path.page.main">
                        <input class="btn btn-primary" type="submit" title="button"
                               value="<fmt:message key="header.logoff" bundle="${resourceBundle}"/>"/>
                    </form>

                    <form class="navbar-form navbar-right" title="user" action="Controller" method="post">
                        <input type="hidden" name="command" value="">
                        <input type="hidden" name="page" value="path.page.user">
                        <!--<input class="btn btn-default" type="submit"  title="button" value="user" /> />-->
                        <button type="submit" class="btn btn-default">
                            <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                                ${user.login}
                        </button>
                    </form>
                </c:if>
            </ul>

        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>

</header>

