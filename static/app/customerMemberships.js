Vue.component("customerMemberships", {
	data: function () {
		return {
			memberships:[{name:"cheap",price:5,description:"You get nothing monthly! 5 times a month."},{name:"expensive",price:20,description:"You get nothing monthly! 15 times a month."},{name:"expensasf",price:50,description:"You get nothing yearly! 200 times a year."}],
			averageGrade: "",
			choosenMembShip: "",
			currentMembership: null,
			id:'None',
			expires:'',
			showJustOpen: false,
			nameSorted: false,
			locationSorted: false,
			averageGradeSorted:false,
			loggedin : null,
		}
	},
	template: ` 
	        <div style="text-align:center;">
	        <table  style="text-align:center;">
	        <tr>
	        	<td>
				<h1>Memberships:</h1>
				</td>
				</tr>
	        		<tr>
	        		<td>	 
	    		<table border="3" style="margin-left:auto;margin-right:auto;height:50%;width:1500px;display:block;">
	    			<thead style="width: 100%;height: 56px; display: inline-block;margin-right:40px;">
			    		<tr bgcolor="grey" style="width:100%;font-size: 40px;">
			    			<th style="max-width:170px;min-width:230px;cursor: pointer;">Membership </th>
			    			<th style="max-width:140px;min-width:140px">Price</th>
			    			<th style="max-width:154px;min-width:1100px;cursor: pointer;">Description </th>
			    		</tr>	
		    		</thead>
		    		<tbody style="width: calc(100% + 20px);height: 300px;display: inline-block; overflow: auto;" class="showa">
			    		<tr style="font-size: 40px;" v-for="(object, index) in this.memberships" v-on:click="ChoosenMem(object)">
			    			<td style="max-width:170px;min-width:230px">{{object.name}}</td>
			    			<td style="max-width:140px;min-width:140px">{{object.price}} e</td>
			    			<td style="max-width:152px;min-width:1100px"> {{object.description}}</td>
			    		</tr>
		    		</tbody>
		    	</table>
						</td>
						<div class="hspace"/>					
						<td>
						<p>Your current paid membership: {{this.id}}</p>
						<p>Expires at:  <br/> {{this.expires}} </p>
						</td>
						</tr>
				<tr>
				<td>
				<div class="vspace"> </div>
		    	<input readonly v-model="choosenMembShip.name" style="font-size: 40px;width: 500px;"></input>
		    	<button v-on:click="pay" style="font-size: 40px;width: 200px;">Pay</button>
				</td>
				<td>
				</td>
				</tr>
		    	</table>
		    </div>
`
	,
	methods : {
		 myFunction : function() {
 		 document.getElementById("myDropdown").classList.toggle("show");
		},
		ChoosenMem : function(obj) {
			this.choosenMembShip = obj;
		},
		pay :function(){
			axios
			.post('membership/createMembership',null,{params: {membership: this.choosenMembShip.name + "", price:  this.choosenMembShip.price + ""}})
			.then(response => (alert("success, refresh page!")));
		},
		initialiseSportObjects : function (data) {
		this.sportObjects = data;
		this.showSportObjects = data;
		},
		logchange : function(data) {
			this.loggedin = data;
			if(this.loggedin)  { 
				axios
					.get('user/getUsername')
					.then(response => this.usernameText = "Welcome " + response.data + "!");
			}else this.usernameText = null;
		},
		init22 : function(data) {
			this.currentMembership = data;
			if(this.currentMembership != null) {
				this.id = this.currentMembership.id;
				this.expires = this.currentMembership.expires;
			}
		}
	},
	mounted () {
		this.choosenMembShip = this.memberships[0];
		axios
			.get('user/getlogged')
			.then(response => (this.logchange(response.data)));
		axios
			.get('membership/getMembership')
			.then(response => (this.init22(response.data)));
    }
});