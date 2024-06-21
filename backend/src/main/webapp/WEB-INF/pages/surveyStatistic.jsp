<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@ page contentType="text/html"
                                                                          pageEncoding="UTF-8"%> <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%-- Document : surveyStatistic Created on : Jun 11, 2024, 12:22:17 PM Author : MAVERICK --%>

<!DOCTYPE html>
<html>
    <head>
        <title>Thống Kê Khảo Sát</title>
        <link
            rel="stylesheet"
            href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
            />
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    </head>
    <body>
<!--        <<h4> ${statistics}</h4>
        <h4> ${personalOpinions}</h4>-->

        <c:url value="/" var="action" />
        <h1 class="text-center text-info mt-1">THỐNG KÊ KHẢO SÁT</h1>
        <section class="container">
            <div class="col-md-4 col-md-offset-3 d-flex flex-row mb-3 gap-2">
                <form action="${action}" method="get" class="search-form w-100">
                    <select class="form-select" aria-label="Default select example" name="type">
                        <option value="0" selected>Kiểu</option>
                        <option value="1">Theo tháng</option>
                        <option value="2">Theo năm</option>
                    </select>
                    <div class="form-group has-feedback mb-2">
                        <input
                            type="text"
                            class="form-control"
                            name="kw"
                            id="search"
                            placeholder="Tìm kiếm"
                            />
                        <span class="glyphicon glyphicon-search form-control-feedback"></span>
                    </div>
                    <button type="submit" class="btn btn-info">Tìm</button>
                </form>
            </div>
        </section>

        <div class="container">
            <div class="row">
                <div class="col-md-7 col-12">
                    <canvas id="combinedChart"></canvas>
                </div>
            </div>
        </div>

        <div class="mt-5">
            <h2>Feedback List</h2>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Date</th>
                        <th>Customer Name</th>
                        <th>Personal Opinion</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="feedback" items="${personalOpinions}">
                        <tr>
                            <td>${feedback.date}</td>
                            <td>${feedback.customer_name}</td>
                            <td>${feedback.personal_opinion}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <script>
            let hygieneSum = 0;
            let serviceSum = 0;
            let infrastructureSum = 0;
            let hygieneCount = 0;
            let serviceCount = 0;
            let infrastructureCount = 0;

            <c:forEach items="${statistics}" var="s">
            if ('${s.type}' === 'hygiene') {
                hygieneSum += parseInt('${s.answer}');
                hygieneCount++;
            } else if ('${s.type}' === 'service') {
                serviceSum += parseInt('${s.answer}');
                serviceCount++;
            } else if ('${s.type}' === 'infrastructure') {
                infrastructureSum += parseInt('${s.answer}');
                infrastructureCount++;
            }
            </c:forEach>

            let hygieneAvg = hygieneCount > 0 ? hygieneSum / hygieneCount : 0;
            let serviceAvg = serviceCount > 0 ? serviceSum / serviceCount : 0;
            let infrastructureAvg = infrastructureCount > 0 ? infrastructureSum / infrastructureCount : 0;

            function drawCombinedBarChart(ctx, hygieneAvg, serviceAvg, infrastructureAvg) {
                new Chart(ctx, {
                    type: 'bar',
                    data: {
                        labels: ['Đánh Giá Vệ Sinh', 'Đánh Giá Dịch Vụ', 'Đánh Giá Cơ Sở Hạ Tầng'],
                        datasets: [{
                                label: 'Trung Bình Đánh Giá',
                                data: [hygieneAvg, serviceAvg, infrastructureAvg],
                                borderWidth: 1,
                                backgroundColor: [
                                    'rgba(75, 192, 192, 0.2)',
                                    'rgba(192, 75, 192, 0.2)',
                                    'rgba(192, 192, 75, 0.2)'
                                ],
                                borderColor: [
                                    'rgba(75, 192, 192, 1)',
                                    'rgba(192, 75, 192, 1)',
                                    'rgba(192, 192, 75, 1)'
                                ]
                            }]
                    },
                    options: {
                        scales: {
                            y: {
                                beginAtZero: true,
                                max: 5
                            }
                        }
                    }
                });
            }

            window.onload = function () {
                let ctx = document.getElementById("combinedChart").getContext('2d');
                drawCombinedBarChart(ctx, hygieneAvg, serviceAvg, infrastructureAvg);
            }
        </script>
    </body>
</html>
