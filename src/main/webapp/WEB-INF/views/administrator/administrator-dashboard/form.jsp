<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<h2>
	<acme:message code="administrator.administrator-dashboard.form.title.components"/>
</h2>

<table class="table table-sm">
	<tr>
		<th scope="row">
			<acme:message code="administrator.administrator-dashboard.form.label.totalNumberOfComponents"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfComponents}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.administrator-dashboard.form.label.averageRetailPriceOfComponents"/>
		</th>
		<td>
			<div>
				<canvas id="averageRetailPriceOfComponents"></canvas>
			</div>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.administrator-dashboard.form.label.deviationRetailPriceOfComponents"/>
		</th>
		<td>
			<div>
				<canvas id="deviationRetailPriceOfComponents"></canvas>
			</div>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.administrator-dashboard.form.label.minimumRetailPriceOfComponents"/>
		</th>
		<td>
			<div>
				<canvas id="minimumRetailPriceOfComponents"></canvas>
			</div>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.administrator-dashboard.form.label.maximumRetailPriceOfComponents"/>
		</th>
		<td>
			<div>
				<canvas id="maximumRetailPriceOfComponents"></canvas>
			</div>
		</td>
	</tr>	
</table>
<h2>
	<acme:message code="administrator.administrator-dashboard.form.title.tools"/>
</h2>

<table class="table table-sm">
	<tr>
		<th scope="row">
			<acme:message code="administrator.administrator-dashboard.form.label.totalNumberOfTools"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfTools}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.administrator-dashboard.form.label.averageRetailPriceOfTools"/>
		</th>
		<td>
			<div>
				<canvas id="averageRetailPriceOfTools"></canvas>
			</div>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.administrator-dashboard.form.label.deviationRetailPriceOfTools"/>
		</th>
		<td>
			<div>
				<canvas id=deviationRetailPriceOfTools></canvas>
			</div>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.administrator-dashboard.form.label.minimumRetailPriceOfTools"/>
		</th>
		<td>
			<div>
				<canvas id="minimumRetailPriceOfTools"></canvas>
			</div>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.administrator-dashboard.form.label.maximumRetailPriceOfTools"/>
		</th>
		<td>
			<div>
				<canvas id="maximumRetailPriceOfTools"></canvas>
			</div>
		</td>
	</tr>	
</table>
<h2>
	<acme:message code="administrator.administrator-dashboard.form.title.patronages"/>
</h2>
<table class="table table-sm">
	<tr>
		<th scope="row">
			<acme:message code="administrator.administrator-dashboard.form.label.totalNumberOfPatronages"/>
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
			<acme:message code="administrator.administrator-dashboard.form.label.deviationBudgetOfPatronages"/>
		</th>
		<td>
			<div>
				<canvas id="deviationBudgetOfPatronages"></canvas>
			</div>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.administrator-dashboard.form.label.minimumBudgetOfPatronages"/>
		</th>
		<td>
			<div>
				<canvas id="minimumBudgetOfPatronages"></canvas>
			</div>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.administrator-dashboard.form.label.maximumBudgetOfPatronages"/>
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
		
		let labels_averageRetailPriceOfComponents = [
			<jstl:forEach items="${averageRetailPriceOfComponents.keySet()}" var="x">
				"<acme:print value="${x.getFirst()}"/>-<acme:print value="${x.getSecond()}"/>",
			</jstl:forEach>
			]
		let data_averageRetailPriceOfComponents = [
			<jstl:forEach items="${averageRetailPriceOfComponents.entrySet()}" var="x">
				<jstl:out value="${x.getValue()}"/>,
			</jstl:forEach>
		]
		newChart(labels_averageRetailPriceOfComponents, data_averageRetailPriceOfComponents, "averageRetailPriceOfComponents","line");
		
		
		
		let labels_deviationRetailPriceOfComponents = [
			<jstl:forEach items="${deviationRetailPriceOfComponents.keySet()}" var="x">
				"<acme:print value="${x.getFirst()}"/>-<acme:print value="${x.getSecond()}"/>",
			</jstl:forEach>
			]
		let data_deviationRetailPriceOfComponents = [
			<jstl:forEach items="${deviationRetailPriceOfComponents.entrySet()}" var="x">
				<jstl:out value="${x.getValue()}"/>,
			</jstl:forEach>
		]
		newChart(labels_deviationRetailPriceOfComponents, data_deviationRetailPriceOfComponents, "deviationRetailPriceOfComponents","line");
		
		let labels_minimumRetailPriceOfComponents = [
			<jstl:forEach items="${minimumRetailPriceOfComponents.keySet()}" var="x">
				"<acme:print value="${x.getFirst()}"/>-<acme:print value="${x.getSecond()}"/>",
			</jstl:forEach>
			]
		let data_minimumRetailPriceOfComponents = [
			<jstl:forEach items="${minimumRetailPriceOfComponents.entrySet()}" var="x">
				<jstl:out value="${x.getValue()}"/>,
			</jstl:forEach>
		]
		newChart(labels_minimumRetailPriceOfComponents, data_minimumRetailPriceOfComponents, "minimumRetailPriceOfComponents","bar");
		
		
		let labels_maximumRetailPriceOfComponents = [
			<jstl:forEach items="${maximumRetailPriceOfComponents.keySet()}" var="x">
				"<acme:print value="${x.getFirst()}"/>-<acme:print value="${x.getSecond()}"/>",
			</jstl:forEach>
			]	
		let data_maximumRetailPriceOfComponents = [
			<jstl:forEach items="${maximumRetailPriceOfComponents.entrySet()}" var="x">
				<jstl:out value="${x.getValue()}"/>,
			</jstl:forEach>
		]
		newChart(labels_maximumRetailPriceOfComponents, data_maximumRetailPriceOfComponents, "maximumRetailPriceOfComponents","bar");
		
		
		
		
		let labels_averageRetailPriceOfTools = [
			<jstl:forEach items="${averageRetailPriceOfTools.keySet()}" var="x">
				"<acme:print value="${x}"/>",
			</jstl:forEach>
			]
		let data_averageRetailPriceOfTools = [
			<jstl:forEach items="${averageRetailPriceOfTools.entrySet()}" var="x">
				<jstl:out value="${x.getValue()}"/>,
			</jstl:forEach>
		]
		newChart(labels_averageRetailPriceOfTools, data_averageRetailPriceOfTools, "averageRetailPriceOfTools","line");
		
		
		
		let labels_deviationRetailPriceOfTools = [
			<jstl:forEach items="${deviationRetailPriceOfTools.keySet()}" var="x">
				"<acme:print value="${x}"/>",
			</jstl:forEach>
			]
		let data_deviationRetailPriceOfTools = [
			<jstl:forEach items="${deviationRetailPriceOfTools.entrySet()}" var="x">
				<jstl:out value="${x.getValue()}"/>,
			</jstl:forEach>
		]
		newChart(labels_deviationRetailPriceOfTools, data_deviationRetailPriceOfTools, "deviationRetailPriceOfTools","line");
		

		let labels_minimumRetailPriceOfTools = [
			<jstl:forEach items="${minimumRetailPriceOfTools.keySet()}" var="x">
				"<acme:print value="${x}"/>",
			</jstl:forEach>
			]		
		let data_minimumRetailPriceOfTools = [
			<jstl:forEach items="${minimumRetailPriceOfTools.entrySet()}" var="x">
				<jstl:out value="${x.getValue()}"/>,
			</jstl:forEach>
		]
		newChart(labels_minimumRetailPriceOfTools, data_minimumRetailPriceOfTools, "minimumRetailPriceOfTools","bar");
		
		
		let labels_maximumRetailPriceOfTools = [
			<jstl:forEach items="${maximumRetailPriceOfTools.keySet()}" var="x">
				"<acme:print value="${x}"/>",
			</jstl:forEach>
			]		
		let data_maximumRetailPriceOfTools = [
			<jstl:forEach items="${maximumRetailPriceOfTools.entrySet()}" var="x">
				<jstl:out value="${x.getValue()}"/>,
			</jstl:forEach>
		]
		newChart(labels_maximumRetailPriceOfTools, data_maximumRetailPriceOfTools, "maximumRetailPriceOfTools","bar");
		
		
		
		
				
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

