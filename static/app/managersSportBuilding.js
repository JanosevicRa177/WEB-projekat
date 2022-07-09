Vue.component("managersSportBuilding", {
	data: function () {
		    return {
				name: "",
				surname: "",
				username: "",
				userType: "",
				customerType: "",
				sportBuilding:"",
				allUsers: [],
				customerUsers: [],
				customers:null,
				coaches:null,
				nameSorted: false,
				surnameSorted: false,
				usernameSorted: false,
				pointsSorted: false
		    }
	},
	template: ` 
<div style="text-align:center;">
    <table style="margin-left:auto;margin-right:auto;">
	    <tr colspan="3">
	    	<td style="font-size: 65px;padding: 40px 0px;">Sport Building: {{this.sportBuilding}}</td>
	    </tr>
	    <tr rowspan= "2">
	    	<td colspan ="1">
	    		<p style="font-size:45px;"> Coaches in sport building: {{this.sportBuilding}}</p>
	    		<table border="3" style="margin-left:auto;margin-right:auto;width:811px;display:block;font-size:25px;">
	    			<thead style="width: 100%;height: 56px; display: inline-block;margin-right:40px;">
			    		<tr bgcolor="grey" style="width:100%;font-size: 20px;">
			    			<th style="max-width:140px;min-width:140px;cursor: pointer;" v-on:click="sortByName">Name &#x2191&#x2193</th>
			    			<th style="max-width:170px;min-width:170px;cursor: pointer;" v-on:click="sortBySurname">Surname &#x2191&#x2193</th>
			    			<th style="max-width:150px;min-width:150px;cursor: pointer;" v-on:click="sortByUsername">Username &#x2191&#x2193</th>
			    			<th style="max-width:120px;min-width:120px;">Birth date</th>
			    			<th style="max-width:70px;min-width:70px;">Gender</th>
			    			<th style="max-width:125px;min-width:123px">User Type</th>
			    		</tr>	
		    		</thead>
		    		<tbody style="width: calc(100% + 20px);height: 480px;display: inline-block; overflow: auto;" class="showa">
			    		<tr v-for="(object, index) in this.allUsers">
			    			<td style="max-width:140px;min-width:140px">{{object.name}}</td>
			    			<td style="max-width:170px;min-width:170px">{{object.surname}}</td>
			    			<td style="max-width:150px;min-width:150px">{{object.username}}</td>
			    			<td style="max-width:120px;min-width:120px">{{object.birthDate}}</td>
			    			<td style="max-width:70px;min-width:70px">{{object.gender}}</td>
			    			<td style="max-width:125px;min-width:123px">{{object.userType}}</td>
			    		</tr>
		    		</tbody>
		    	</table>
	    	</td>
	    	<td>
	    	<div class="space"> </div>
	    	<td>
	    	<td colspan ="1">
	    		<p style="font-size:45px;"> Customers in sport building: {{this.sportBuilding}}</p>
	    		<table border="3" style="margin-left:auto;margin-right:auto;width:1072px;display:block;font-size:25px;">
	    			<thead style="width: 100%;height: 56px; display: inline-block;margin-right:40px;">
			    		<tr bgcolor="grey" style="width:100%;font-size: 20px;">
			    			<th style="max-width:140px;min-width:140px;" >Name </th>
			    			<th style="max-width:170px;min-width:170px;" >Surname</th>
			    			<th style="max-width:150px;min-width:150px;" >Username</th>
			    			<th style="max-width:120px;min-width:120px;">Birth date</th>
			    			<th style="max-width:70px;min-width:70px;">Gender</th>
			    			<th style="max-width:120px;min-width:120px;" >Points </th>
			    			<th style="max-width:125px;min-width:123px;">Customer Type</th>
			    			<th style="max-width:125px;min-width:123px">User Type</th>
			    		</tr>	
		    		</thead>
		    		<tbody style="width: calc(100% + 20px);height: 480px;display: inline-block; overflow: auto;" class="showa">
			    		<tr v-for="(object, index) in this.customerUsers">
			    			<td style="max-width:140px;min-width:140px">{{object.name}}</td>
			    			<td style="max-width:170px;min-width:170px">{{object.surname}}</td>
			    			<td style="max-width:150px;min-width:150px">{{object.username}}</td>
			    			<td style="max-width:120px;min-width:120px">{{object.birthDate}}</td>
			    			<td style="max-width:70px;min-width:70px">{{object.gender}}</td>
			    			<td style="max-width:120px;min-width:120px">{{object.points}}</td>
			    			<td style="max-width:125px;min-width:125px">{{object.customerType}}</td>
			    			<td style="max-width:125px;min-width:123px">{{object.userType}}</td>
			    		</tr>
		    		</tbody>
		    	</table>
	    	</td>
	    </tr>
    </table>
</div> 
`
	, 
	methods : {
		init : function() {
		},
		sortByNameAscending : function (a, b){
			if ( a.name.toLowerCase() < b.name.toLowerCase()){
    			return -1;
  			}
  			if ( a.name.toLowerCase() > b.name.toLowerCase()){
    			return 1;
  			}
  		return 0;
		},
		sortByNameDescending : function (a, b){
			if ( a.name.toLowerCase() < b.name.toLowerCase()){
    			return 1;
  			}
  			if ( a.name.toLowerCase() > b.name.toLowerCase()){
    			return -1;
  			}
  		return 0;
		},
		sortBySurnameAscending : function (a, b){
			if ( a.surname.toLowerCase() < b.surname.toLowerCase()){
    			return -1;
  			}
  			if ( a.surname.toLowerCase() > b.surname.toLowerCase()){
    			return 1;
  			}
  		return 0;
		},
		sortBySurnameDescending : function (a, b){
			if ( a.username.toLowerCase() < b.username.toLowerCase()){
    			return 1;
  			}
  			if ( a.username.toLowerCase() > b.username.toLowerCase()){
    			return -1;
  			}
  		return 0;
		},
		sortByUsernameAscending : function (a, b){
			if ( a.username.toLowerCase() < b.username.toLowerCase()){
    			return -1;
  			}
  			if ( a.username.toLowerCase() > b.username.toLowerCase()){
    			return 1;
  			}
  		return 0;
		},
		sortByUsernameDescending : function (a, b){
			if ( a.username.toLowerCase() < b.username.toLowerCase()){
    			return 1;
  			}
  			if ( a.username.toLowerCase() > b.username.toLowerCase()){
    			return -1;
  			}
  		return 0;
		},
		sortByPointsAscending : function (a, b){
			if ( a.points < b.points){
    			return -1;
  			}
  			if ( a.points > b.points){
    			return 1;
  			}
  		return 0;
		},
		sortByPointsDescending : function (a, b){
			if ( a.points < b.points){
    			return 1;
  			}
  			if ( a.points > b.points){
    			return -1;
  			}
  		return 0;
		},
		sortByName : function () {
			this.surnameSorted = false;
			this.usernameSorted = false;
			this.pointsSorted = false;
			if(!this.nameSorted){
				this.nameSorted = true;
				this.allUsers.sort(this.sortByNameAscending);
			} else {
				this.nameSorted = false;
				this.allUsers.sort(this.sortByNameDescending);
			}
		},
		sortBySurname : function () {
			this.nameSorted = false;
			this.usernameSorted = false;
			this.pointsSorted = false;
			if(!this.surnameSorted){
				this.surnameSorted = true;
				this.allUsers.sort(this.sortBySurnameAscending);
			} else {
				this.surnameSorted = false;
				this.allUsers.sort(this.sortBySurnameDescending);
			}
		},
		sortByUsername : function () {
			this.nameSorted = false;
			this.surnameSorted = false;
			this.pointsSorted = false;
			if(!this.usernameSorted){
				this.usernameSorted = true;
				this.allUsers.sort(this.sortByUsernameAscending);
			} else {
				this.usernameSorted = false;
				this.allUsers.sort(this.sortByUsernameDescending);
			}
		},
		sortByPoints : function () {
			this.nameSorted = false;
			this.surnameSorted = false;
			this.usernameSorted = false;
			if(!this.pointsSorted){
				this.pointsSorted = true;
				this.allUsers.sort(this.sortByPointsAscending);
			} else {
				this.pointsSorted = false;
				this.allUsers.sort(this.sortByPointsDescending);
			}
		},
		initialiseCustomers : function(data){
			this.customers = data;
			for (const i in this.customers){
				this.customers[i].birthDate = this.customers[i].birthDate.split("-").reverse().join("/");
				this.customerUsers.push(this.customers[i]);
			}
		},
		initialiseCoaches : function(data){
			this.coaches = data;
			for (const i in this.coaches){
				this.coaches[i].points = -1;
				this.coaches[i].customerType = "None";
				this.coaches[i].birthDate = this.coaches[i].birthDate.split("-").reverse().join("/");
				this.allUsers.push(this.coaches[i]);
			}
		}
	},
	mounted () {
		axios
			.get('manager/getManagersSportBuilding')
			.then(response => (this.sportBuilding = response.data))
		axios
			.get('workout/getCoaches')
			.then(response => (this.initialiseCoaches(response.data)));
		axios
			.get('customer/getCustomersSportBuilding')
			.then(response => (this.initialiseCustomers(response.data)));
    }
});