Vue.component("workoutHistoryManager", {
	data: function () {
		return {
			workoutHistories: [],
			showWorkoutHistories: [],
			workoutType: "",
			priceFrom: "",
			priceTo: "",
			dateFrom: "",
			dateTo: "",
			showJustHistory: false,
			priceSorted: false,
			dateSorted:false,
		}
	},
	template: ` 
<div style="text-align:center;margin-top:-20px;">
	<table style="margin-left:auto; margin-right:auto;">
		<td style="padding: 0 40px;">
			<tr>
				<table style="text-align:center;">
						<tr colspan ="2"><p style="font-size: 20px;"></p></tr>
						<tr style="font-size: 40px;"><td colspan ="2">Search/Filter</td></tr>
						<tr style="font-size: 20px;"><td colspan ="2">================================</td></tr>
						<tr style="font-size: 25px;"><td colspan ="2">Workout type</td></tr>
						<tr>
							<td colspan ="2">
								<select name="type" v-model="workoutType" style="font-size: 25px; width: 259px;">
									<option value=""></option>
					            	<option value="Gym">Gym</option>
					            	<option value="Personal">Personal</option>
					            	<option value="Group">Group</option>
			            		</select>
			            	</td>
						</tr>
						<tr style="font-size: 25px;"><td colspan ="2">Date(From-To):</td></tr>
						<tr>
							<td colspan ="2">
								<input type="date" v-model="dateFrom" style="font-size: 20px; width: 145px;"></input> -
								<input type="date" v-model="dateTo" style="font-size: 20px; width: 145px;"></input>
							</td>
						</tr>
						<tr style="font-size: 25px;"><td colspan ="2">Price(From-To):</td></tr>
												<tr>
							<td colspan ="2">
								<input type="number" v-model="priceFrom" style="font-size: 20px; width: 140px;"></input> -
								<input type="number" v-model="priceTo" style="font-size: 20px; width: 140px;"></input>
							</td>
						</tr>
						<tr><p></tr>
						<tr>
							<td>
								<button style="font-size: 20px; width: 145px;margin: 0px 5px 0px 0px;" v-on:click="Search">Search workouts</button>
							</td>
							<td style="font-size: 20px;width:200px"><input type="checkbox" v-model="showJustHistory" class="checkbox">Show just just workout history</input></td>
						</tr>
				</table>
			</tr>
		</td>
		<td style="padding: 0 30px;">
			<div style="text-align:center;">
	        <h2>Future workouts and workout history:</h2>
	    		<table border="3" style="margin-left:auto;margin-right:auto;height:50%;width:608px;display:block;font-size:25px;margin-top:-20px;">
	    			<thead style="width: 100%;height: 30px; display: inline-block;margin-right:40px;">
			    		<tr bgcolor="grey" style="width:100%;font-size: 20px;">
			    			<th style="max-width:140px;min-width:140px">Workout name</th>
			    			<th style="max-width:140px;min-width:140px">Workout type</th>
			    			<th style="max-width:90px;min-width:90px;cursor: pointer;" v-on:click="sortByPrice">Price &#x2191&#x2193</th>
			    			<th style="max-width:140px;min-width:140px;cursor: pointer;" v-on:click="sortByDate">Date &#x2191&#x2193</th>
			    			<th style="max-width:60px;min-width:60px;">Hour</th>
			    		</tr>	
		    		</thead>
		    		<tbody style="width: calc(100% + 20px);height: 450px;display: inline-block; overflow: auto;" class="showa">
			    		<tr v-for="(object, index) in this.showWorkoutHistories" style="height: 60px;">
			    			<td style="max-width:140px;min-width:140px">{{object.workoutName}}</td>
			    			<td style="max-width:140px;min-width:140px">{{object.workoutType}}</td>
			    			<td style="max-width:90px;min-width:90px">{{object.price}}</td>
			    			<td style="max-width:140px;min-width:140px">{{object.checkinDate}}</td>
			    			<td style="max-width:60px;min-width:60px">{{object.hours}}</td>
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
		sortByPriceAscending : function (a, b){
			if ( a.price < b.price){
    			return -1;
  			}
  			if ( a.price > b.price){
    			return 1;
  			}
  		return 0;
		},
		sortByPriceDescending : function (a, b){
			if ( a.price < b.price){
    			return 1;
  			}
  			if ( a.price > b.price){
    			return -1;
  			}
  		return 0;
		},
		sortByDateAscending : function (a, b){
			let dateString1 = a.checkinDate.split("/").reverse().join("-");
			let dateString2 = b.checkinDate.split("/").reverse().join("-");
			let date1 = new Date(dateString1);
			let date2 = new Date(dateString2);
			if ( date1 < date2){
    			return -1;
  			}
  			if ( date1 > date2){
    			return 1;
  			}
  		return 0;
		},
		sortByDateDescending : function (a, b){
			let dateString1 = a.checkinDate.split("/").reverse().join("-");
			let dateString2 = b.checkinDate.split("/").reverse().join("-");
			let date1 = new Date(dateString1);
			let date2 = new Date(dateString2);
			if ( date1 < date2){
    			return 1;
  			}
  			if ( date1 > date2){
    			return -1;
  			}
  		return 0;
		},
		sortByDate : function () {
			this.priceSorted = false;
			if(!this.dateSorted){
			this.dateSorted = true;
			this.showWorkoutHistories.sort(this.sortByDateAscending);
			} else {
			this.dateSorted = false;
			this.showWorkoutHistories.sort(this.sortByDateDescending);
			}
		},
		sortByPrice : function () {
			this.dateSorted = false;
			if(!this.priceSorted){
			this.priceSorted = true;
			this.showWorkoutHistories.sort(this.sortByPriceAscending);
			} else {
			this.priceSorted = false;
			this.showWorkoutHistories.sort(this.sortByPriceDescending);
			}
		},
		Search : function () {
			this.showWorkoutHistories = [];
			let today = new Date();
			if(this.workoutType === "" && this.priceFrom === "" && this.priceTo === ""
			 && this.dateFrom === "" && this.dateTo === ""){
				if(this.showJustHistory){
					for (const i in this.workoutHistories){
						let workoutDateString = this.workoutHistories[i].checkinDate.split("/").reverse().join("-");
						let workoutDate = new Date(workoutDateString);
						if(workoutDate < today){
							this.showWorkoutHistories.push(this.workoutHistories[i])
						}
					}
				} else this.showWorkoutHistories = this.workoutHistories;
			} else {
				let shouldAddbyWorkoutType = true;
				let shouldAddbyPriceFrom = true;
				let shouldAddbyPriceTo = true;
				let shouldAddbyDateFrom = true;
				let shouldAddbyDateTo = true;
				for (const i in this.workoutHistories){
					if(this.workoutType != ""){
						if(this.workoutHistories[i].workoutType != this.workoutType){
							shouldAddbyWorkoutType = false;
						}
					}
					if(this.priceFrom != ""){
						if(this.workoutHistories[i].price < this.priceFrom){
							shouldAddbyPriceFrom = false;
						}
					}
					if(this.priceTo != ""){
						if(this.workoutHistories[i].price > this.priceTo){
							shouldAddbyPriceTo = false;
						}
					}
					if(this.dateFrom != ""){
						let date = new Date(this.dateFrom);
						let workoutDateString = this.workoutHistories[i].checkinDate.split("/").reverse().join("-");
						let workoutDate = new Date(workoutDateString);
						if(workoutDate < date){
							shouldAddbyDateFrom = false;
						}
					}
					if(this.dateTo != ""){
						let date = new Date(this.dateTo);
						let workoutDateString = this.workoutHistories[i].checkinDate.split("/").reverse().join("-");
						let workoutDate = new Date(workoutDateString);
						if(workoutDate > date){
							shouldAddbyDateTo = false;
						}
					}
					if(this.showJustHistory){
						let workoutDateString = this.workoutHistories[i].checkinDate.split("/").reverse().join("-");
						let workoutDate = new Date(workoutDateString);
						if(shouldAddbyWorkoutType && shouldAddbyPriceFrom
						&& shouldAddbyPriceTo && shouldAddbyDateFrom && shouldAddbyDateTo && (workoutDate < today)){
						this.showWorkoutHistories.push(this.workoutHistories[i]);
						}
					}
					else{
						if(shouldAddbyWorkoutType && shouldAddbyPriceFrom
						&& shouldAddbyPriceTo && shouldAddbyDateFrom && shouldAddbyDateTo){
						this.showWorkoutHistories.push(this.workoutHistories[i]);
						}
					}
					shouldAddbyWorkoutType = true;
					shouldAddbyPriceFrom = true;
					shouldAddbyPriceTo = true;
					shouldAddbyDateFrom = true;
					shouldAddbyDateTo = true;
				}
			}
		},
		initialiseWorkoutHistory : function (data) {
		this.workoutHistories = data;
		for (const i in this.workoutHistories){
			this.workoutHistories[i].checkinDate = this.workoutHistories[i].checkinDate.split("-").reverse().join("/");
		}
		this.showWorkoutHistories = this.workoutHistories;
		},
	},
	mounted () {
		axios
			.get('workoutHistory/getAllByManager')
			.then(response => (this.initialiseWorkoutHistory(response.data)));
	
    }
});