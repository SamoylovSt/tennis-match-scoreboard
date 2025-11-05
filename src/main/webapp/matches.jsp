<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tennis Scoreboard | Finished Matches</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">

    <script src="js/app.js"></script>
</head>

<body>
<header class="header">
    <section class="nav-header">
        <div class="brand">
            <div class="nav-toggle">
                <img src="images/menu.png" alt="Logo" class="logo">
            </div>
            <span class="logo-text">TennisScoreboard</span>
        </div>
        <div>
            <nav class="nav-links">
                <a class="nav-link" href="/index.html">Home</a>
                <a class="nav-link" href="/matches">Matches</a>
            </nav>
        </div>
    </section>
</header>
<main>
    <div class="container">
        <h1>Matches</h1>
<div class="input-container">
    <form method="GET" action="${pageContext.request.contextPath}/matches">
        <input class="input-filter" name="name" placeholder="Filter by name" type="text"
               value="${param.name}" />
        <div>
            <button type="submit" class="btn-filter">Apply Filter</button>
            <a href="${pageContext.request.contextPath}/matches">
                <button type="button" class="btn-filter">Reset Filter</button>
            </a>
        </div>
    </form>
</div>

        <table class="table-matches">
            <tr>
                <th>Player One</th>
                <th>Player Two</th>
                <th>Winner</th>
            </tr>
            <c:forEach var="match" items="${matches}">
            <tr>
                <td>${match.playerOneName}</td>
                <td>${match.playerTwoName}</td>
                <td><span class="winner-name-td">${match.winnerName}</span></td>
            </tr>
            </c:forEach>
        </table>

  <!-- Пагинация -->
  <div class="pagination">
      <!-- Кнопка "Назад" -->
      <c:choose>
          <c:when test="${currentPage > 1}">
              <a class="prev" href="?page=${currentPage - 1}"> &lt; </a>
          </c:when>
          <c:otherwise>
              <span class="prev disabled"> &lt; </span>
          </c:otherwise>
      </c:choose>

      <!-- Номера страниц -->
      <c:forEach begin="1" end="${totalPages}" var="i">
          <c:choose>
              <c:when test="${i == currentPage}">
                  <span class="num-page current">${i}</span>
              </c:when>
              <c:otherwise>
                  <a class="num-page" href="?page=${i}">${i}</a>
              </c:otherwise>
          </c:choose>
      </c:forEach>

      <!-- Кнопка "Вперед" -->
      <c:choose>
          <c:when test="${currentPage < totalPages}">
              <a class="next" href="?page=${currentPage + 1}"> &gt; </a>
          </c:when>
          <c:otherwise>
              <span class="next disabled"> &gt; </span>
          </c:otherwise>
      </c:choose>
  </div>
</main>
<footer>
    <div class="footer">
        <p>&copy; Tennis Scoreboard, project from <a href="https://zhukovsd.github.io/java-backend-learning-course/">zhukovsd/java-backend-learning-course</a>
            roadmap.</p>
    </div>
</footer>
</body>
</html>
