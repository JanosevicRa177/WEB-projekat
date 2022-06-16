Vue.component("index", {
	data: function () {
		return {
			sportObjects: null
		}
	},
	template: ` 
<div style="text-align:center;">
	<h1 style="font-size: 63px;">WELCOME TO SPORT ARENA</h1>
       	<button v-on:click="ShowLoginForm" style="font-size: 35px; width: 200px;margin: 0px 10px;"> Login </button>
        <button v-on:click="ShowRegisterForm" style="font-size: 35px; width: 200px;margin: 0px 10px;"> Register </button>
        <h2>Sport objects:</h2>
    		<table border="3" style="margin-left:auto; margin-right:auto;height:380px;width:100%;display:block;background-color: #161515;color:#EA2900;">
    			<thead style="width: 100%;height: 52px; display: inline-block;">
		    		<tr bgcolor="lightgrey" style="width:100%;font-size: 18px;">
		    			<th style="width:10%;">Name</th>
		    			<th style="width:10%">Type</th>
		    			<th style="width:15%">Location</th>
		    			<th style="width:35%">Logo</th>
		    			<th style="width:5%">Average grade</th>
		    			<th style="width:10%">Work time</th>
		    			<th style="width:1%"></th>
		    		</tr>	
	    		</thead>
	    		<tbody style="width: 99.8%;height: 320px;display: inline-block; overflow: auto;">
		    		<tr v-for="(object, index) in this.sportObjects">
		    			<td style="width:10%;">{{object.name}}</td>
		    			<td style="width:10%;">{{object.type}}</td>
		    			<td style="width:15%;">
		    			<p>{{object.location.longitude}}, {{object.location.latitude}}</p>
		    			<p>{{object.location.address.street}}, {{object.location.address.number}}, {{object.location.address.city}}, {{object.location.address.zipCode}}</p>
		    			</td>
		    			<td style="width:35%"><img :src="object.image" style="width:200 px; height:200px;"></td>
		    			<td style="width:5%">{{object.averageGrade}}</td>
		    			<td style="width:10%">{{object.workTime}}</td>
		    		</tr>
	    		</tbody>
	    	</table>
</div> 
`
	,
	methods : { 
		ShowLoginForm : function () {
			router.push(`/login`);
		},
		ShowRegisterForm : function () {
			router.push(`/register`);
		}
	},
	mounted () {
		axios
			.get('sportBuilding/getAll')
			.then(response => (this.sportObjects = response.data))

    }
});