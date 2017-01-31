<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${local}"/>
<fmt:setBundle basename="def" var="resourceBundle"/>

<html>
<head>
    <!--InternetExplorer link  -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta title="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="/css/bootstrap-slider.css">
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>


<body>
<%@ include file="/jsp/header.jsp" %>

    <div class="container">
        <div class="container">
            <h1 class="page-header">Edit Movie</h1>
            <div class="row">

                <form action="/UploadServlet" method="post" enctype="multipart/form-data">
                    <div class="control-group">
                        <div class="panel panel-primary control-label">
                            <div class="panel-heading">Icon:</div>
                            <div class="panel-body">
                                <input class="addIcon" type="file" name="file"  onchange="this.form.submit()"/>
                                <input  type="submit" hidden />
                            </div>
                        </div>
                    </div>
                </form>


                <form id="addmovie" title="addmovie" action="Controller" method="post">
                    <input type="hidden" name="command" value="addmovie">
                    <input type="hidden" name="page" value="path.page.main">

                    <div class="col-lg-6 col-xs-12 col-md-4">

                        <div class="control-group">
                            <label class="control-label" for="title">Title:</label>
                            <div class="controls">
                                <input type="text" id="title" name="title" placeholder="" class="input-xlarge">
                            </div>
                        </div>
                        <div class="space"></div>
                        <div class="control-group">
                            <label class="control-label" for="description">Description:</label>
                            <div class="controls">
                                <textarea type="text" id="description" name="description" placeholder=""
                                          class="input-xlarge" rows="5"></textarea>
                            </div>
                        </div>
                        <div class="space"></div>
                        <div class="control-group">
                            <label class="control-label" for="country">Country:</label>
                            <div class="controls">
                                <input type="text" id="country" name="country" placeholder="" class="input-xlarge">
                            </div>
                        </div>
                        <div class="space"></div>
                        <div class="control-group">
                            <label class="control-label" for="movieYear">Year:</label>
                            <div class="controls">
                                <select id="movieYear"  name="movieYear">
                                    <c:forEach var="i" begin="1970" end="2017">
                                        <option>${i}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="space"></div>
                        <div class="control-group">
                            <label class="control-label">Rating:</label>
                            <div class="controls">
                                <input id="ex6" type="text" data-slider-min="0" data-slider-max="9.9"
                                       data-slider-step="0.1" data-slider-value="5" form="addmovie" name="rating"/>
                                <span id="ex6CurrentSliderValLabel">Rating:<span id="ex6SliderVal" class="label-danger">3</span></span>
                            </div>
                        </div>
                        <div class="space"></div>

                        <input id="subMovie" class="btn btn-danger btn-lg" type="submit" name="button" value="Add Movie"/>

                    </div>


                    <div class="col-lg-6 col-xs-12 col-sm-4 col-md-4 ">
                        <div class="panel panel-default">
                            <ul class="list-group">
                                <c:forEach var="genre" items="${sessionScope.genrelist}">
                                    <li class="list-group-item">
                                            ${genre}
                                        <div class="material-switch pull-right">
                                            <input form="addmovie" id="${genre}" name="${genre}" type="checkbox"/>
                                            <label for="${genre}" class="label-info"></label>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </form>
            </div>

        </div>
    </div>

<form title="back" action="Controller" method="post">
    <input type="hidden" name="command" value="">
    <input type="hidden" name="page" value="path.page.main">
    <input class="btn btn-primary" type="submit"  name="button" value="<fmt:message key="back" bundle="${resourceBundle}"/>" />
</form>


    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->

    <script src="/js/jquery-3.1.1.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->


    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/bootstrap-slider.js"></script>
<script src="/js/custom.js"></script>

    <script type="text/javascript">

        $("#ex6").slider();
        $("#ex6").on("slide", function(slideEvt) {
            $("#ex6SliderVal").text(slideEvt.value);
        });


    </script>

</body>
</html>
