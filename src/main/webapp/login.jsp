<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Login MVC</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background: #243b55;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            margin: 0;
        }

        .login-box {
            background: white;
            padding: 35px;
            width: 320px;
            border-radius: 12px;
        }

        h1 {
            text-align: center;
            color: #243b55;
        }

        label {
            display: block;
            margin: 15px 0 6px;
        }

        input {
            width: 100%;
            padding: 10px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 6px;
        }

        button {
            width: 100%;
            padding: 12px;
            margin-top: 22px;
            background: #243b55;
            color: white;
            border: none;
            border-radius: 6px;
            cursor: pointer;
        }

        .message {
            text-align: center;
            color: #b42318;
        }
    </style>
</head>

<body>
<div class="login-box">
    <h1>Connexion</h1>

    <% if ("error".equals(request.getParameter("status"))) { %>
    <p class="message">
        Login ou mot de passe incorrect.
    </p>
    <% } %>

    <form action="${pageContext.request.contextPath}/login"
          method="post">

        <label for="login">Login :</label>
        <input type="text" id="login" name="login" required>

        <label for="password">Mot de passe :</label>
        <input type="password" id="password"
               name="password" required>

<label for="version">Version à tester :</label>
<select id="version" name="version">
    <option value="secure" selected>Version sécurisée</option>
    <option value="vulnerable">Version vulnérable</option>
</select>

        <button type="submit">Se connecter</button>
    </form>
</div>
</body>
</html>
```