Vue.component("index", {
	data: function () {
		return {
			sportObjects: null,
			loggedin : null,
			log:null,
			loggedUser:null
		}
	},
	template: ` 
<div style="text-align:center;">
	<div style="text-align:center; margin-left:30%;">
	<h1 style="font-size: 63px;">WELCOME TO SPORT ARENA</h1>
       	<button v-on:click="LoginLogofFunction" style="font-size: 35px; width: 200px;margin: 0px 10px;" >{{log}}</button>
        <button v-on:click="ShowRegisterForm" style="font-size: 35px; width: 200px;margin: 0px 10px;" :style="{visibility: loggedin ? 'hidden' : 'visible'}"> Register </button>
        <h2>Sport objects:</h2>
    		<table border="3" style="margin-left:auto;margin-right:auto;height:50%;width:97.5%;display:block;background-color: #161515;color:#EA2900;">
    			<thead style="width: 98.2%;height: 56px; display: inline-block;margin-right:40px;">
		    		<tr bgcolor="grey" style="width:100%;font-size: 20px;">
		    			<th style="max-width:170px;min-width:170px">Name</th>
		    			<th style="max-width:140px;min-width:140px">Type</th>
		    			<th style="max-width:140px;min-width:140px">Location</th>
		    			<th style="max-width:300px;min-width:300px">Logo</th>
		    			<th style="max-width:75px;min-width:75px">Average grade</th>
		    			<th style="max-width:120px;min-width:120px">Work time</th>
		    		</tr>	
	    		</thead>
	    		<tbody style="width: 100%;height: 318px;display: inline-block; overflow: auto;">
		    		<tr v-for="(object, index) in this.sportObjects">
		    			<td style="max-width:170px;min-width:170px">{{object.name}}</td>
		    			<td style="max-width:140px;min-width:140px">{{object.type}}</td>
		    			<td style="max-width:140px;min-width:140px">
		    			<p>{{object.location.longitude}}, {{object.location.latitude}}</p>
		    			<p>{{object.location.address.street}}, {{object.location.address.number}}, {{object.location.address.city}}, {{object.location.address.zipCode}}</p>
		    			</td>
		    			<td style="max-width:300px;min-width:300px"><img :src="object.image" style="width:200px; height:200px;"></td>
		    			<td style="max-width:75px;min-width:75px">{{object.averageGrade}}</td>
		    			<td style="max-width:120px;min-width:120px">{{object.workTime}}</td>
		    		</tr>
	    		</tbody>
	    	</table>
	    </div>
</div> 
`
	,
	methods : {
		ShowRegisterForm : function () {
			router.push(`/register`);
		},
		LoginLogofFunction : function(){
			if(this.loggedin) {
				if (!confirm('Are you sure you wanna log off?'));				
				else {
					this.loggedin = false;
					axios
						.get('customer/logoff');
					this.log = "Login";
				}
			}
			else router.push(`/login`);
		},
		logchange : function(data) {
			this.loggedin = data
			if(this.loggedin) this.log = "Log off";
			else this.log = "Login";
		}
	},
	mounted () {
		axios
			.get('sportBuilding/getAll')
			.then(response => (this.sportObjects = response.data));
		axios
			.get('user/getlogged')
			.then(response => (this.logchange(response.data)));
		
    }
});