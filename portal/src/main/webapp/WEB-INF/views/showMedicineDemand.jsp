<%@ include file="common/header.jspf" %>

	<style>
		@charset "UTF-8";
		@import url(https://fonts.googleapis.com/css?family=Open+Sans:300,400,700);

		body {
			font-weight: 300;
			line-height: 1.42em;
			color: #A7A1AE;
		}

		h1 {
			font-size: 3em;
			font-weight: 300;
			line-height: 1em;
			text-align: center;
			color: #e2312b;
		}

		h2 {
			font-size: 1em;
			font-weight: 300;
			text-align: center;
			display: block;
			line-height: 1em;
			padding-bottom: 2em;
			color: #FB667A;
		}

		h2 a {
			font-weight: 700;
			text-transform: uppercase;
			color: #FB667A;
			text-decoration: none;
		}

		.blue {
			color: #185875;
		}

		.yellow {
			color: #FFF842;
		}

		.container th h1 {
			font-weight: bold;
			font-size: 1em;
			text-align: left;
			color: #185875;
		}

		.container td {
			font-weight: normal;
			font-size: 1em;
			color: #0E1119;
			border: 1px solid black;
		}

		.container tr th {
			border: 1px solid black;
		}


		.container td,
		.container th {
			padding-bottom: 2%;
			padding-top: 2%;
			padding-left: 0.5%;
		}

		/* Background-color of the odd rows */

		/* Background-color of the even rows */


		.container td:first-child {
			color: #FB667A;
		}

		.container tr:hover {
			background-color: #464A52;
			-webkit-box-shadow: 0 6px 6px -6px #0E1119;
			-moz-box-shadow: 0 6px 6px -6px #0E1119;
			box-shadow: 0 6px 6px -6px #0E1119;
		}

		.container td:hover {
			background-color: #FFF842;
			color: #403E10;
			font-weight: bold;

			box-shadow: #7F7C21 -1px 1px, #7F7C21 -2px 2px, #7F7C21 -3px 3px, #7F7C21 -4px 4px, #7F7C21 -5px 5px, #7F7C21 -6px 6px;
			transform: translate3d(6px, -6px, 0);

			transition-delay: 0s;
			transition-duration: 0.4s;
			transition-property: all;
			transition-timing-function: line;
		}

		@media (max-width: 800px) {

			.container td:nth-child(4),
			.container th:nth-child(4) {
				display: none;
			}
		}
	</style>

	<div class="container" style="height: 60px;">

	</div>

	<article>
		<div class="container">
			<h1 class="article-heading text-center m-4">Medicine Demand</h1>
			<table class="container">
				<thead>
					<tr>
						<th>Medicine</th>
						<th>Demand Count</th>
					</tr>
				</thead>
				<tbody></tbody>
				<c:forEach items="${medicineDemandList}" var="medicineDemand">
					<tr>
						<td class="col-right">${medicineDemand.medicineName}</td>
						<td class="col-right">${medicineDemand.demandCount}</td>
					</tr>

				</c:forEach>
				</tbody>
			</table>
		</div>
	</article>


	<%@ include file="common/footer.jspf" %>