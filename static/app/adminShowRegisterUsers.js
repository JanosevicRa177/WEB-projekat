Vue.component("adminShowRegisterUsers", {
	data: function () {
		    return {
				name: "",
				surname: "",
				username: "",
				userType: "",
				customerType: "",
				allUsers: [],
				customers: null,
				managers: null,
				admins: null,
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
	    <tr>
	    	<td colspan="3" style="font-size: 65px;padding: 40px 0px;">Users</td>
	    	<td>
	    	<div style="text-align:right;">
	    	<button style="font-size: 25px; width: 300px; height: 100px; margin: 0px 10px;" >Register managers / coaches</button>
	    	</div> 
			</td>
	    </tr>
	    <tr>
	    	<td>
	    		<table style="text-align:center;padding: 0px 30px;">
					<tr colspan ="2"><p style="font-size: 20px;"></p></tr>
					<tr style="font-size: 40px;"><td colspan ="2">Search/Filter</td></tr>
					<tr style="font-size: 20px;"><td colspan ="2">================================</td></tr>
					<tr style="font-size: 30px;"><td colspan ="2">Name:</td></tr>
					<tr><td colspan ="2"><input type="text" v-model="name" style="font-size: 25px; width: 250px;" name="name"></input></td></tr>
					<tr style="font-size: 30px;"><td colspan ="2">Surname:</td></tr>
					<tr><td colspan ="2"><input type="text" v-model="surname" style="font-size: 25px; width: 250px;" name="surname"></input></td></tr>
					<tr style="font-size: 30px;"><td colspan ="2">Username:</td></tr>
					<tr><td colspan ="2"><input type="text" v-model="username" style="font-size: 25px; width: 250px;" name="username"></input></td></tr>
					<tr style="font-size: 30px;"><td colspan ="2">User Type:</td></tr>
					<tr>
						<td colspan ="2">
							<select name="type" v-model="userType" style="font-size: 25px; width: 259px;">
								<option value=""></option>
				            	<option value="Admin">Admin</option>
				            	<option value="Coach">Coach</option>
				            	<option value="Manager">Manager</option>
				            	<option value="Customer">Customer</option>
		            		</select>
		            	</td>
					</tr>
					<tr style="font-size: 30px;"><td colspan ="2">Customer Type:</td></tr>
					<tr>
						<td colspan ="2">
							<select name="type" v-model="customerType" style="font-size: 25px; width: 259px;">
								<option value=""></option>
				            	<option value="Gold">Gold</option>
				            	<option value="Silver">Silver</option>
				            	<option value="Bronze">Bronze</option>
		            		</select>
		            	</td>
					</tr>
					<tr>
						<td>
							<button style="font-size: 20px; width: 180px;margin: 0px 5px 0px 0px;" v-on:click="Search">Search users</button>
						</td>
					</tr>
				</table>
	    	</td>
	    	<td colspan ="3">
	    		<p style="font-size:45px;"></p>
	    		<table border="3" style="margin-left:auto;margin-right:auto;width:1072px;display:block;font-size:25px;">
	    			<thead style="width: 100%;height: 56px; display: inline-block;margin-right:40px;">
			    		<tr bgcolor="grey" style="width:100%;font-size: 20px;">
			    			<th style="max-width:140px;min-width:140px;cursor: pointer;" v-on:click="sortByName">Name &#x2191&#x2193</th>
			    			<th style="max-width:170px;min-width:170px;cursor: pointer;" v-on:click="sortBySurname">Surname &#x2191&#x2193</th>
			    			<th style="max-width:150px;min-width:150px;cursor: pointer;" v-on:click="sortByUsername">Username &#x2191&#x2193</th>
			    			<th style="max-width:120px;min-width:120px;">Birth date</th>
			    			<th style="max-width:70px;min-width:70px;">Gender</th>
			    			<th style="max-width:120px;min-width:120px;" v-on:click="sortByPoints">Points &#x2191&#x2193</th>
			    			<th style="max-width:125px;min-width:123px;">Customer Type</th>
			    			<th style="max-width:125px;min-width:123px">User Type</th>
			    		</tr>	
		    		</thead>
		    		<tbody style="width: calc(100% + 20px);height: 500px;display: inline-block; overflow: auto;" class="showa">
			    		<tr v-for="(object, index) in this.allUsers">
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
		Search : function () {
			this.allUsers = [];
			this.allUsersToCheck = [];
			this.allUsersToCheck.push.apply(this.allUsersToCheck,this.customers);
			this.allUsersToCheck.push.apply(this.allUsersToCheck,this.admins);
			this.allUsersToCheck.push.apply(this.allUsersToCheck,this.managers);
			this.allUsersToCheck.push.apply(this.allUsersToCheck,this.coaches);
			let shouldAddbyName = true;
			let shouldAddbySurname= true;
			let shouldAddbyUsername = true;
			let shouldAddbyUserType = true;
			let shouldAddbyCustomerType = true;
			for (const i in this.allUsersToCheck){
				if(this.name != ""){
					if(!this.allUsersToCheck[i].name.toLowerCase().includes(this.name.toLowerCase())){
						shouldAddbyName = false;
						}
					}
				if(this.surname != ""){
					if(!this.allUsersToCheck[i].surname.toLowerCase().includes(this.surname.toLowerCase())){
						shouldAddbySurname = false;
						}
					}
				if(this.username != ""){
					if(!this.allUsersToCheck[i].username.toLowerCase().includes(this.username.toLowerCase())){
						shouldAddbyUsername = false;
						}
					}
				if(this.userType != ""){
					if(this.allUsersToCheck[i].userType != this.userType){
						shouldAddbyUserType = false;
						}
					}
				if(this.customerType != ""){
					if(this.allUsersToCheck[i].customerType != this.customerType){
						shouldAddbyUserType = false;
						}
					}
				if(shouldAddbyName && shouldAddbySurname && shouldAddbyUsername && shouldAddbyUserType && shouldAddbyCustomerType){
				this.allUsers.push(this.allUsersToCheck[i]);
				}
				shouldAddbyName = true;
				shouldAddbySurname= true;
				shouldAddbyUsername = true;
				shouldAddbyUserType = true;
				shouldAddbyCustomerType = true;
				}
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
				this.allUsers.push(this.customers[i]);
			}
		},
		initialiseManagers : function(data){
			this.managers = data;
			for (const i in this.managers){
				this.managers[i].points = -1;
				this.managers[i].customerType = "None";
				this.managers[i].birthDate = this.managers[i].birthDate.split("-").reverse().join("/");
				this.allUsers.push(this.managers[i]);
			}
		},
		initialiseAdmins : function(data){
			this.admins = data;
			for (const i in this.admins){
				this.admins[i].points = -1;
				this.admins[i].customerType = "None";
				this.admins[i].birthDate = this.admins[i].birthDate.split("-").reverse().join("/");
				this.allUsers.push(this.admins[i]);
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
			.get('customer/getAll')
			.then(response => (this.initialiseCustomers(response.data)));
		axios
			.get('manager/getAll')
			.then(response => (this.initialiseManagers(response.data)));
		axios
			.get('admin/getAll')
			.then(response => (this.initialiseAdmins(response.data)));
		axios
			.get('coach/getAll')
			.then(response => (this.initialiseCoaches(response.data)));
    }
});