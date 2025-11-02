<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tennis Scoreboard | Winner</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../../css/style.css">
    <style>
        .winner-container {
            max-width: 600px;
            margin: 50px auto;
            padding: 20px;
            text-align: center;
            background-color: #f8f9fa;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        .winner-image {
            max-width: 100%;
            height: auto;
            margin: 20px 0;
        }
        .winner-title {
            font-size: 36px;
            font-weight: bold;
            color: #28a745;
            margin: 10px 0;
        }
        .winner-name {
            font-size: 48px;
            font-weight: bold;
            color: #007bff;
            margin: 20px 0;
            padding: 15px;
            background-color: #e9ecef;
            border-radius: 8px;
            border: 2px dashed #28a745;
        }
        .congratulations {
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
        .trophy {
            font-size: 64px;
            margin: 10px 0;
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
                <a class="nav-link" href="/matches" target="_self">Matches</a>
            </nav>
        </div>
    </section>
</header>

<main>
    <div class="winner-container">

        <img src="../../images/cup.png" alt="Winner Celebration" class="winner-image">

        <div class="winner-title">Match Winner</div>

        <c:choose>
            <c:when test="${not empty winnerName}">
                <div class="winner-name">${winnerName}</div>
                <div class="congratulations">Congratulations on your victory!</div>
            </c:when>
            <c:otherwise>
                <div class="winner-name">${loserName}</div>
                <div class="congratulations">Defeat</div>
            </c:otherwise>
        </c:choose>

        <div style="margin-top: 20px;">
            <a href="/" class="home-link">Return to Homepage</a>
            <a href="/matches" class="home-link" style="margin-left: 10px;">View All Matches</a>
        </div>
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