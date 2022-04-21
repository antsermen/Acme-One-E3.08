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
			<acme:message code="administrator.administrator-dashboard.form.label.totalNumberOfPatronagesByStatus"/>
		</th>
		<td>
			<div>
				<canvas id="totalNumberOfPatronagesByStatus"></canvas>
			</div>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.administrator-dashboard.form.label.averageBudgetOfPatronagesByStatus"/>
		</th>
		<td>
			<div>
				<canvas id="averageBudgetOfPatronagesByStatus"></canvas>
			</div>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.administrator-dashboard.form.label.deviationBudgetOfPatronagesByStatus"/>
		</th>
		<td>
			<div>
				<canvas id="deviationBudgetOfPatronagesByStatus"></canvas>
			</div>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.administrator-dashboard.form.label.minimumBudgetOfPatronagesByStatus"/>
		</th>
		<td>
			<div>
				<canvas id="minimumBudgetOfPatronagesByStatus"></canvas>
			</div>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.administrator-dashboard.form.label.maximumBudgetOfPatronagesByStatus"/>
		</th>
		<td>
			<div>
				<canvas id="maximumBudgetOfPatronagesByStatus"></canvas>
			</div>
		</td>
	</tr>	
</table>
<script type="text/javascript">
	$(document).ready(function() {
		function createChart(labels_list, datas_list, id, chartType){
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
		let EURtechnology = "EURtechnology";
		let EURmadera = "EURmadera";
		
		let labels_averageRetailPriceOfComponents = [
			<jstl:forEach items="${averageRetailPriceOfComponents.keySet()}" var="x">
			<acme:print value="${x.getFirst()}"/><acme:print value="${x.getSecond()}"/>,

		</jstl:forEach>
			]
		let data_averageRetailPriceOfComponents = [
			<jstl:forEach items="${averageRetailPriceOfComponents.entrySet()}" var="x">
			<jstl:out value="${x.getValue()}"/>,
		</jstl:forEach>
		]
		createChart(labels_averageRetailPriceOfComponents, data_averageRetailPriceOfComponents, "averageRetailPriceOfComponents","line");
		
		
		
		let labels_deviationRetailPriceOfComponents = [
			<jstl:forEach items="${deviationRetailPriceOfComponents.keySet()}" var="x">
			<acme:print value="${x.getFirst()}"/><acme:print value="${x.getSecond()}"/>,

		</jstl:forEach>
			]
		let data_deviationRetailPriceOfComponents = [
			<jstl:forEach items="${deviationRetailPriceOfComponents.entrySet()}" var="x">
			<jstl:out value="${x.getValue()}"/>,
		</jstl:forEach>
		]
		createChart(labels_deviationRetailPriceOfComponents, data_deviationRetailPriceOfComponents, "deviationRetailPriceOfComponents","line");
		
				let labels_minimumRetailPriceOfComponents = [
					<jstl:forEach items="${minimumRetailPriceOfComponents.keySet()}" var="x">
					<acme:print value="${x.getFirst()}"/><acme:print value="${x.getSecond()}"/>,

				</jstl:forEach>
					]
				let data_minimumRetailPriceOfComponents = [
					<jstl:forEach items="${minimumRetailPriceOfComponents.entrySet()}" var="x">
					<jstl:out value="${x.getValue()}"/>,
				</jstl:forEach>
				]
				createChart(labels_minimumRetailPriceOfComponents, data_minimumRetailPriceOfComponents, "minimumRetailPriceOfComponents","bar");
				
		
		let labels_maximumRetailPriceOfComponents = [
			<jstl:forEach items="${maximumRetailPriceOfComponents.keySet()}" var="x">
			<acme:print value="${x.getFirst()}"/><acme:print value="${x.getSecond()}"/>,

		</jstl:forEach>
			]	
		let data_maximumRetailPriceOfComponents = [
			<jstl:forEach items="${maximumRetailPriceOfComponents.entrySet()}" var="x">
			<jstl:out value="${x.getValue()}"/>,
		</jstl:forEach>
		]
		createChart(labels_maximumRetailPriceOfComponents, data_maximumRetailPriceOfComponents, "maximumRetailPriceOfComponents","bar");

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		let EUR = "EUR";
		let GBP = "GBP";
		let USD = "USD";
		
		let labels_averageRetailPriceOfTools = [
			<jstl:forEach items="${averageRetailPriceOfTools.keySet()}" var="x">
			<acme:print value="${x.toString()}"/>,

		</jstl:forEach>
			]
		let data_averageRetailPriceOfTools = [
			<jstl:forEach items="${averageRetailPriceOfTools.entrySet()}" var="x">
			<jstl:out value="${x.getValue()}"/>,
		</jstl:forEach>
		]
		createChart(labels_averageRetailPriceOfTools, data_averageRetailPriceOfTools, "averageRetailPriceOfTools","line");
		
		
		
		let labels_deviationRetailPriceOfTools = [
			<jstl:forEach items="${deviationRetailPriceOfTools.keySet()}" var="x">
			<acme:print value="${x.toString()}"/>,
		</jstl:forEach>
			]
		let data_deviationRetailPriceOfTools = [
			<jstl:forEach items="${deviationRetailPriceOfTools.entrySet()}" var="x">
			<jstl:out value="${x.getValue()}"/>,
		</jstl:forEach>
		]
		createChart(labels_deviationRetailPriceOfTools, data_deviationRetailPriceOfTools, "deviationRetailPriceOfTools","line");
		

				let labels_maximumRetailPriceOfTools = [
					<jstl:forEach items="${maximumRetailPriceOfTools.keySet()}" var="x">
					<acme:print value="${x.toString()}"/>,

				</jstl:forEach>
					]		
				let data_maximumRetailPriceOfTools = [
					<jstl:forEach items="${maximumRetailPriceOfTools.entrySet()}" var="x">
					<jstl:out value="${x.getValue()}"/>,
				</jstl:forEach>
				]
				createChart(labels_maximumRetailPriceOfTools, data_maximumRetailPriceOfTools, "maximumRetailPriceOfTools","bar");

				
			
		
		let ACCEPTED = "ACCEPTED";
		let PROPOSED = "PROPOSED";
		let DENIED = "DENIED";
		
		
		let labels_totalNumberOfPatronagesByStatus = [
			<jstl:forEach items="${totalNumberOfPatronagesByStatus.keySet()}" var="x">
			<acme:print value="${x.toString()}"/>,

		</jstl:forEach>
			]
		let data_totalNumberOfPatronagesByStatus = [
			<jstl:forEach items="${totalNumberOfPatronagesByStatus.entrySet()}" var="x">
			<jstl:out value="${x.getValue()}"/>,
		</jstl:forEach>
		]	
		createChart(labels_totalNumberOfPatronagesByStatus, data_totalNumberOfPatronagesByStatus, "totalNumberOfPatronagesByStatus","pie");
		
		
		
		let labels_averageBudgetOfPatronagesByStatus = [
			<jstl:forEach items="${averageBudgetOfPatronagesByStatus.keySet()}" var="x">
			<acme:print value="${x.toString()}"/>,

		</jstl:forEach>
			]
		let data_averageBudgetOfPatronagesByStatus = [
			<jstl:forEach items="${averageBudgetOfPatronagesByStatus.entrySet()}" var="x">
			<jstl:out value="${x.getValue()}"/>,
		</jstl:forEach>
		]
		createChart(labels_averageBudgetOfPatronagesByStatus, data_averageBudgetOfPatronagesByStatus, "averageBudgetOfPatronagesByStatus", "line");
		
		
		
		
		let labels_deviationBudgetOfPatronagesByStatus = [
			<jstl:forEach items="${deviationBudgetOfPatronagesByStatus.keySet()}" var="x">
			<acme:print value="${x.toString()}"/>,

		</jstl:forEach>
			]
		let data_deviationBudgetOfPatronagesByStatus = [
			<jstl:forEach items="${deviationBudgetOfPatronagesByStatus.entrySet()}" var="x">
			<jstl:out value="${x.getValue()}"/>,
		</jstl:forEach>
		]
		createChart(labels_deviationBudgetOfPatronagesByStatus, data_deviationBudgetOfPatronagesByStatus, "deviationBudgetOfPatronagesByStatus","line");
		
		
		
		
		
		let labels_minimumBudgetOfPatronagesByStatus = [
			<jstl:forEach items="${minimumBudgetOfPatronagesByStatus.keySet()}" var="x">
			<acme:print value="${x.toString()}"/>,

		</jstl:forEach>
			]	
		let data_minimumBudgetOfPatronagesByStatus = [
			<jstl:forEach items="${minimumBudgetOfPatronagesByStatus.entrySet()}" var="x">
			<jstl:out value="${x.getValue()}"/>,
		</jstl:forEach>
		]
		createChart(labels_minimumBudgetOfPatronagesByStatus, data_minimumBudgetOfPatronagesByStatus, "minimumBudgetOfPatronagesByStatus","bar");
		
		
		
		
		
		let labels_maximumBudgetOfPatronagesByStatus = [
			<jstl:forEach items="${maximumBudgetOfPatronagesByStatus.keySet()}" var="x">
			<acme:print value="${x.toString()}"/>,

		</jstl:forEach>
			]	
		let data_maximumBudgetOfPatronagesByStatus = [
			<jstl:forEach items="${maximumBudgetOfPatronagesByStatus.entrySet()}" var="x">
			<jstl:out value="${x.getValue()}"/>,
		</jstl:forEach>
		]
		createChart(labels_maximumBudgetOfPatronagesByStatus, data_maximumBudgetOfPatronagesByStatus, "maximumBudgetOfPatronagesByStatus","bar");
		
		
		
		
		
		
		let labels_minimumRetailPriceOfTools = [
			<jstl:forEach items="${minimumRetailPriceOfTools.keySet()}" var="x">
			<acme:print value="${x.toString()}"/>,

		</jstl:forEach>
			]		
		let data_minimumRetailPriceOfTools = [
			<jstl:forEach items="${minimumRetailPriceOfTools.entrySet()}" var="x">
			<jstl:out value="${x.getValue()}"/>,
		</jstl:forEach>
		]
		createChart(labels_minimumRetailPriceOfTools, data_minimumRetailPriceOfTools, "minimumRetailPriceOfTools","bar");
		
		
		
	});
</script>



<acme:return/>

