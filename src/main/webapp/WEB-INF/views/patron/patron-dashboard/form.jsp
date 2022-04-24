<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>


<h2>
	<acme:message code="patron.patron-dashboard.form.title.patronages"/>
</h2>
<table class="table table-sm">
	<tr>
		<th scope="row">
			<acme:message code="patron.patron-dashboard.form.label.totalNumberOfPatronages"/>
		</th>
		<td>
			<div>
				<canvas id="totalNumberOfPatronages"></canvas>
			</div>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.administrator-dashboard.form.label.averageBudgetOfPatronages"/>
		</th>
		<td>
			<div>
				<canvas id="averageBudgetOfPatronages"></canvas>
			</div>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="patron.patron-dashboard.form.label.deviationBudgetOfPatronages"/>
		</th>
		<td>
			<div>
				<canvas id="deviationBudgetOfPatronages"></canvas>
			</div>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="patron.patron-dashboard.form.label.minimumBudgetOfPatronages"/>
		</th>
		<td>
			<div>
				<canvas id="minimumBudgetOfPatronages"></canvas>
			</div>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="patron.patron-dashboard.form.label.maximumBudgetOfPatronages"/>
		</th>
		<td>
			<div>
				<canvas id="maximumBudgetOfPatronages"></canvas>
			</div>
		</td>
	</tr>	
</table>
<script type="text/javascript">
	$(document).ready(function() {
		function newChart(labels_list, datas_list, id, chartType){
			var data = {
				
				labels : labels_list,
				datasets : [
					{
						data : datas_list,
						backgroundColor: [
							"#109DFA", 
							"#E7D40D",
							"#6DC36D",
							"#EF280F",
							"#FF689D"
							]
					}
				]
			};
			var options = {
					
				scales : {
					yAxes : [
						{
							ticks : {
								suggestedMin : 0.0,
								suggestedMax : 1.0
							}
						}
					]
				},
				legend : {
					display : false
				}
			};
			var canvas, context;
			canvas = document.getElementById(id);
			context = canvas.getContext("2d");
			new Chart(context, {
				type : chartType,
				data : data,
				options : options
			});
			   
		}
		
		let labels_totalNumberOfPatronages = [
			<jstl:forEach items="${totalNumberOfPatronages.keySet()}" var="x">
				"<acme:print value="${x}"/>",
			</jstl:forEach>
			]
		let data_totalNumberOfPatronages = [
			<jstl:forEach items="${totalNumberOfPatronages.entrySet()}" var="x">
				<jstl:out value="${x.getValue()}"/>,
			</jstl:forEach>
		]	
		newChart(labels_totalNumberOfPatronages, data_totalNumberOfPatronages, "totalNumberOfPatronages","pie");
		

		let labels_averageBudgetOfPatronages = [
			<jstl:forEach items="${averageBudgetOfPatronages.keySet()}" var="x">
				"<acme:print value="${x.getFirst()}"/>-<acme:print value="${x.getSecond()}"/>",
			</jstl:forEach>
			]
		let data_averageBudgetOfPatronages = [
			<jstl:forEach items="${averageBudgetOfPatronages.entrySet()}" var="x">
				<jstl:out value="${x.getValue()}"/>,
			</jstl:forEach>
		]
		newChart(labels_averageBudgetOfPatronages, data_averageBudgetOfPatronages, "averageBudgetOfPatronages", "line");
		
		let labels_deviationBudgetOfPatronages = [
			<jstl:forEach items="${deviationBudgetOfPatronages.keySet()}" var="x">
				"<acme:print value="${x.getFirst()}"/>-<acme:print value="${x.getSecond()}"/>",
			</jstl:forEach>
			]
		let data_deviationBudgetOfPatronages = [
			<jstl:forEach items="${deviationBudgetOfPatronages.entrySet()}" var="x">
				<jstl:out value="${x.getValue()}"/>,
			</jstl:forEach>
		]
		newChart(labels_deviationBudgetOfPatronages, data_deviationBudgetOfPatronages, "deviationBudgetOfPatronages","line");
			
		let labels_minimumBudgetOfPatronages = [
			<jstl:forEach items="${minimumBudgetOfPatronages.keySet()}" var="x">
				"<acme:print value="${x.getFirst()}"/>-<acme:print value="${x.getSecond()}"/>",
			</jstl:forEach>
			]	
		let data_minimumBudgetOfPatronages = [
			<jstl:forEach items="${minimumBudgetOfPatronages.entrySet()}" var="x">
				<jstl:out value="${x.getValue()}"/>,
			</jstl:forEach>
		]
		newChart(labels_minimumBudgetOfPatronages, data_minimumBudgetOfPatronages, "minimumBudgetOfPatronages","bar");
		
		let labels_maximumBudgetOfPatronages = [
			<jstl:forEach items="${maximumBudgetOfPatronages.keySet()}" var="x">
				"<acme:print value="${x.getFirst()}"/>-<acme:print value="${x.getSecond()}"/>",
			</jstl:forEach>
			]	
		let data_maximumBudgetOfPatronages = [
			<jstl:forEach items="${maximumBudgetOfPatronages.entrySet()}" var="x">
				<jstl:out value="${x.getValue()}"/>,
			</jstl:forEach>
		]
		newChart(labels_maximumBudgetOfPatronages, data_maximumBudgetOfPatronages, "maximumBudgetOfPatronages","bar");
		
	});
	
	
	
</script>



<acme:return/>

