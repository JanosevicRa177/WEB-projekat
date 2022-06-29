Vue.component("adminShowRegisterUsers", {
	data: function () {
		    return {
				name: "",
				surname: "",
				username: ""
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
							<select name="type" v-model="userType" style="font-size: 25px; width: 259px;">
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
			    			<th style="max-width:120px;min-width:120px;">Points</th>
			    			<th style="max-width:125px;min-width:123px;">Customer Type</th>
			    			<th style="max-width:125px;min-width:123px">User Type</th>
			    		</tr>	
		    		</thead>
		    		<tbody style="width: calc(100% + 25px);height: 500px;display: inline-block; overflow: auto;" class="showa">
			    		<tr v-for="(object, index) in this.showSportObjects" v-on:click="Alert(object)">
			    			<td style="max-width:170px;min-width:170px">{{object.name}}</td>
			    			<td style="max-width:140px;min-width:140px">{{object.type}}</td>
			    			<td style="max-width:150px;min-width:150px">
			    			<p>{{object.location.longitude}}, {{object.location.latitude}}</p>
			    			<p>{{object.location.address.street}}, {{object.location.address.number}}, {{object.location.address.city}}, {{object.location.address.zipCode}}</p>
			    			</td>
			    			<td style="max-width:285px;min-width:285px"><img :src="object.image" style="width:200px; height:200px;"></td>
			    			<td style="max-width:70px;min-width:80px">{{object.averageGrade}}</td>
			    			<td style="max-width:125px;min-width:125px">{{object.workTime}}</td>
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
	},
	mounted () {
    }
});