<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>📃Blog</title>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
            <link rel="stylesheet" href="css/style.css">
        </head>

        <body>

            <nav class="navbar navbar-expand-sm bg-warning navbar-dark mb-3">
                <div class="container-fluid">
                    <a class="navbar-brand" href="/">Blog</a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                        data-bs-target="#collapsibleNavbar">
                        <span class="navbar-toggler-icon"></span>
                    </button>

                    <div class="collapse navbar-collapse justify-content-between" id="collapsibleNavbar">

                        <ul class="navbar-nav">
                            <li class="nav-item">
                                <a class="nav-link" href="/loginForm">로그인</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/joinForm">회원가입</a>
                            </li>
                        </ul>

                    </div>
                </div>
            </nav>

            <h1>main page</h1>
            <div class="mt-3 p-5 bg-warning text-white text-center" style="margin-bottom: 0">
                <p>Created by HeeSun</p>
                <p>📞 010-2222-7777</p>
                <p>🏳 부산 수영구 XX동</p>
            </div>
        </body>

        </html>