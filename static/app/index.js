Vue.component("index", {
	data: function () {
		return {
			sportObjects: null,
			showSportObjects:null,
			usernameText: null,
			name: "",
			type: "",
			city: "",
			averageGrade: "",
			showJustOpen: false,
			nameSorted: false,
			locationSorted: false,
			averageGradeSorted:false,
			loggedin : null,
		}
	},
	template: ` 
<div style="text-align:center;margin-top:-20px;">
	<table style="margin-left:auto; margin-right:auto;" >
		<td style="padding: 0 40px;">
			<tr>
				<p>{{usernameText}}</p>
			</tr>
			<tr>
				<table style="text-align:center;">
						<tr colspan ="2"><p style="font-size: 20px;"></p></tr>
						<tr style="font-size: 40px;"><td colspan ="2">Search/Filter</td></tr>
						<tr style="font-size: 20px;"><td colspan ="2">================================</td></tr>
						<tr style="font-size: 30px;"><td colspan ="2">Name:</td></tr>
						<tr><td colspan ="2"><input type="text" v-model="name" style="font-size: 25px; width: 250px;" name="name"></input></td></tr>
						<tr style="font-size: 30px;"><td colspan ="2">Type</td></tr>
						<tr>
							<td colspan ="2">
								<select name="type" v-model="type" style="font-size: 25px; width: 259px;">
									<option value=""></option>
					            	<option value="Gym">Gym</option>
					            	<option value="Pool">Pool</option>
					            	<option value="sportCenter">SportCenter</option>
					            	<option value="danceStudio">danceStudio</option>
			            		</select>
			            	</td>
						</tr>
						<tr style="font-size: 30px;"><td colspan ="2">City:</td></tr>
						<tr><td colspan ="2"><input type="text" v-model="city" style="font-size: 25px; width: 250px;" name="city"></input></td></tr>
						<tr style="font-size: 30px;"><td colspan ="2">Average grade:</td></tr>
						<tr><td colspan ="2"><input type="text" v-model="averageGrade" style="font-size: 25px; width: 250px;" name="grade"></input><p></p></td></tr>
						<tr>
							<td>
								<button style="font-size: 20px; width: 145px;margin: 0px 5px 0px 0px;" v-on:click="Search">Search buildings</button>
							</td>
							<td style="font-size: 20px;"><input type="checkbox" v-model="showJustOpen" class="checkbox">Show just open buildings</input></td>
						</tr>
				</table>
			</tr>
		</td>
		<td style="padding: 0 30px;">
			<div style="text-align:center;">
			<h1 style="font-size: 63px;">WELCOME TO SPORT ARENA</h1>
	        <h2>Sport objects:</h2>
	    		<table border="3" style="margin-left:auto;margin-right:auto;height:50%;width:1010px;display:block;font-size:25px">
	    			<thead style="width: 100%;height: 56px; display: inline-block;margin-right:40px;">
			    		<tr bgcolor="grey" style="width:100%;font-size: 20px;">
			    			<th style="max-width:170px;min-width:170px;cursor: pointer;" v-on:click="sortByName">Name &#x2191&#x2193</th>
			    			<th style="max-width:140px;min-width:140px">Type</th>
			    			<th style="max-width:154px;min-width:154px;cursor: pointer;" v-on:click="sortByLocation">Location &#x2191&#x2193</th>
			    			<th style="max-width:300px;min-width:300px">Logo</th>
			    			<th style="max-width:70px;min-width:80px;cursor: pointer;" v-on:click="sortByAverageGrade">Average grade &#x2191&#x2193</th>
			    			<th style="max-width:125px;min-width:123px">Work time</th>
			    		</tr>	
		    		</thead>
		    		<tbody style="width: calc(100% + 20px);height: 313px;display: inline-block; overflow: auto;" class="showa">
			    		<tr v-for="(object, index) in this.showSportObjects" v-on:click="ShowSportBuiling(object)">
			    			<td style="max-width:170px;min-width:170px">{{object.name}}</td>
			    			<td style="max-width:140px;min-width:140px">{{object.type}}</td>
			    			<td style="max-width:152px;min-width:152px">
			    			<p>{{object.location.longitude}}, {{object.location.latitude}}</p>
			    			<p>{{object.location.address.street}}, {{object.location.address.number}}, {{object.location.address.city}}, {{object.location.address.zipCode}}</p>
			    			</td>
			    			<td style="max-width:300px;min-width:300px"><img :src="object.image" style="width:200px; height:200px;"></td>
			    			<td style="max-width:70px;min-width:80px">{{object.averageGrade}}</td>
			    			<td style="max-width:125px;min-width:125px">{{object.workTime}}</td>
			    		</tr>
		    		</tbody>
		    	</table>
		    </div>
		</td>
	</table>
</div> 
`
	,
	methods : {
		 myFunction : function() {
 		 document.getElementById("myDropdown").classList.toggle("show");
		},
		sortByAverageGradeAscending : function (a, b){
			if ( a.averageGrade < b.averageGrade){
    			return -1;
  			}
  			if ( a.averageGrade > b.averageGrade){
    			return 1;
  			}
  		return 0;
		},
		sortByAverageGradeDescending : function (a, b){
			if ( a.averageGrade < b.averageGrade){
    			return 1;
  			}
  			if ( a.averageGrade > b.averageGrade){
    			return -1;
  			}
  		return 0;
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
		sortByLocationAscending : function (a, b){
			if ( a.location.address.city.toLowerCase() < b.location.address.city.toLowerCase()){
    			return -1;
  			}
  			if ( a.location.address.city.toLowerCase() > b.location.address.city.toLowerCase()){
    			return 1;
  			}
  		return 0;
		},
		sortByLocationDescending : function (a, b){
			if ( a.location.address.city.toLowerCase() < b.location.address.city.toLowerCase()){
    			return 1;
  			}
  			if ( a.location.address.city.toLowerCase() > b.location.address.city.toLowerCase()){
    			return -1;
  			}
  		return 0;
		},
		ShowSportBuiling : function (object) {
			router.push({ path: '/ShowSportBuilding', query: { sportBuildingName: object.name } });
		},
		sortByLocation : function () {
			this.nameSorted = false;
			this.averageGradeSorted = false;
			if(!this.locationSorted){
			this.locationSorted = true;
			this.showSportObjects.sort(this.sortByLocationAscending);
			} else {
			this.locationSorted = false;
			this.showSportObjects.sort(this.sortByLocationDescending);
			}
		},
		sortByAverageGrade : function () {
			this.locationSorted = false;
			this.nameSorted = false;
			if(!this.averageGradeSorted){
			this.averageGradeSorted = true;
			this.showSportObjects.sort(this.sortByAverageGradeAscending);
			} else {
			this.averageGradeSorted = false;
			this.showSportObjects.sort(this.sortByAverageGradeDescending);
			}
		},
		sortByName : function () {
			this.locationSorted = false;
			this.averageGradeSorted = false;
			if(!this.nameSorted){
				this.nameSorted = true;
				this.showSportObjects.sort(this.sortByNameAscending);
			} else {
				this.nameSorted = false;
				this.showSportObjects.sort(this.sortByNameDescending);
			}
		},
		Search : function () {
			this.showSportObjects = [];
			if(this.name === "" && this.type === "" && this.city === "" && this.averageGrade === ""){
				if(this.showJustOpen){
					for (const i in this.sportObjects){
						if(this.sportObjects[i].status == "Open"){
							this.showSportObjects.push(this.sportObjects[i])
						}
					}
				}else this.showSportObjects = this.sportObjects;
			} else {
				let shouldAddbyName = true;
				let shouldAddbyType= true;
				let shouldAddbyCity = true;
				let shouldAddbyAverageGrade = true;
				for (const i in this.sportObjects){
					if(this.name != ""){
						if(!this.sportObjects[i].name.toLowerCase().includes(this.name.toLowerCase())){
							shouldAddbyName = false;
						}
					}
					if(this.type != ""){
						if(this.sportObjects[i].type != this.type){
							shouldAddbyType = false;
						}
					}
					if(this.averageGrade != ""){
						if(this.sportObjects[i].averageGrade != this.averageGrade){
							shouldAddbyAverageGrade = false;
						}
					}
					if(this.city != ""){
						if(!this.sportObjects[i].location.address.city.toLowerCase().includes(this.city.toLowerCase())){
							shouldAddbyCity = false;
						}
					}
					if(this.showJustOpen){
						if(shouldAddbyName && shouldAddbyType && shouldAddbyAverageGrade && shouldAddbyCity && this.sportObjects[i].status == "Open"){
						this.showSportObjects.push(this.sportObjects[i]);
						}
					}
					else{
						if(shouldAddbyName && shouldAddbyType && shouldAddbyAverageGrade && shouldAddbyCity){
						this.showSportObjects.push(this.sportObjects[i]);
						}
					}
					shouldAddbyName = true;
					shouldAddbyType= true;
					shouldAddbyCity = true;
					shouldAddbyAverageGrade = true;
				}
			}
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
	},
	mounted () {
		axios
			.get('sportBuilding/getAll')
			.then(response => (this.initialiseSportObjects(response.data)));
		axios
			.get('user/getlogged')
			.then(response => (this.logchange(response.data)));
	
    }
});