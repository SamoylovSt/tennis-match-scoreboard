<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Tennis Scoreboard | Error</title>
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="../../css/style.css">
  <style>
    .error-container {
      max-width: 600px;
      margin: 50px auto;
      padding: 20px;
      text-align: center;
      background-color: #f8f9fa;
      border-radius: 8px;
      box-shadow: 0 2px 10px rgba(0,0,0,0.1);
    }
    .error-image {
      max-width: 100%;
      height: auto;
      margin: 20px 0;
    }
    .error-code {
      font-size: 48px;
      font-weight: bold;
      color: #dc3545;
      margin: 10px 0;
    }
    .error-message {
      font-size: 18px;
      color: #6c757d;
      margin: 15px 0;
    }
    .home-link {
      display: inline-block;
      margin-top: 20px;
      padding: 10px 20px;
      background-color: #007bff;
      color: white;
      text-decoration: none;
      border-radius: 4px;
      transition: background-color 0.3s;
    }
    .home-link:hover {
      background-color: #0056b3;
    }
  </style>
</head>
<body>
<header class="header">
  <section class="nav-header">
    <div class="brand">
      <div class="nav-toggle">
        <img src="../../images/menu.png" alt="Logo" class="logo">
      </div>
      <span class="logo-text">TennisScoreboard</span>
    </div>
    <div>
      <nav class="nav-links">
        <a class="nav-link" href="/" target="_self">Home</a>
        <a class="nav-link" href="matches?page=1" target="_self">Matches</a>
      </nav>
    </div>
  </section>
</header>

<main>
  <div class="error-container">
    <img src="../../images/server_error.jpg" alt="Error Illustration" class="error-image">

    <c:if test="${not empty errorCode}">
      <div class="error-code">CODE ${errorCode}</div>
    </c:if>

    <c:choose>
      <c:when test="${not empty errorMessage}">
        <div class="error-message">${errorMessage}</div>
      </c:when>
      <c:otherwise>
        <div class="error-message">"not ok"</div>
      </c:otherwise>
    </c:choose>

    <a href="/" class="home-link">Return to Homepage</a>
  </div>
</main>
<script src="../../js/app.js"></script>
<footer>
  <div class="footer">
    <p>&copy; Tennis Scoreboard, project from <a href="https://zhukovsd.github.io/java-backend-learning-course/">zhukovsd/java-backend-learning-course</a> roadmap.</p>
  </div>
</footer>
</body>
</html>