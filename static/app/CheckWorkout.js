Vue.component("checkWorkout", {
	data: function () {
		    return {
				checkedPersonal: true,
				checkedGroup: false,
				personalContents: null,
				groupContents: null,
				date: "",
				dateNotChecked: true,
				workoutNotChecked: true,
				hourNotValid: true,
				cantSubmit: true,
				cantGroupSubmit: true,
				hour:"",
				selectedPersonalWorkout: {name: ""},
				selectedGroupWorkout: {workout: ""}
		    }
	},
	template: ` 
<div style="text-align:center;">
	<div style="text-align:center;width:calc(100% + 60px);height: 50px;margin-left:-30px;">
	<table class="checkbar">
		<td style="text-align:center;width:50%;">
			<button style="text-align:center;width:100%;" class ="checkButtons" v-on:click="CheckPersonal">Personal / Gym training</button>
		</td>
		<td style="text-align:center;width:50%;">
 			<button style="text-align:center;width:100%;" class ="checkButtons" v-on:click="CheckGroup">Group training</button>
		</td>
    </table>
	</div> 
	<table style="margin-left:auto; margin-right:auto;">
	<td v-if="checkedPersonal">
		<p>Possible Personal/Gym trainings</p>
		<table border="3" style="margin-left:auto;margin-right:auto;height:50%;width:1249px;display:block;font-size:25px;margin-top:-15px;">
			<thead style="width: 100%;height: 29px; display: inline-block;margin-right:40px;">
	    		<tr bgcolor="grey" style="width:100%;font-size: 20px;">
	    			<th style="max-width:170px;min-width:170px;">Name</th>
	    			<th style="max-width:140px;min-width:140px">Type</th>
	    			<th style="max-width:300px;min-width:300px">Image</th>
	    			<th style="max-width:170px;min-width:170px;">Coach</th>
	    			<th style="max-width:170px;min-width:170px;">Description</th>
	    			<th style="max-width:110px;min-width:110px;">Duration</th>
	    			<th style="max-width:140px;min-width:140px;">Price</th>
	    		</tr>
    		</thead>
    		<tbody style="width: calc(100% + 20px);height: 400px;display: inline-block; overflow: auto;" class="showa">
	    		<tr v-for="(object, index) in this.personalContents" v-on:click="SelectPersonal(object)">
	    			<td style="max-width:170px;min-width:170px">{{object.name}}</td>
	    			<td style="max-width:140px;min-width:140px">{{object.type}}</td>
	    			<td style="max-width:300px;min-width:300px"><img :src="object.image" style="width:200px; height:200px;"></td>
	    			<td style="max-width:170px;min-width:170px">{{object.coachUsername}}</td>
	    			<td style="max-width:170px;min-width:170px">{{object.description}}</td>
	    			<td style="max-width:110px;min-width:110px">{{object.duration}}</td>
	    			<td style="max-width:140px;min-width:140px;">{{object.price}}</td>
	    		</tr>
    		</tbody>
    	</table>
    	<table>
    		<tr style="margin-top:-15px;">
				<td>
					<p style="height: 0px;text-align:left;">Date:</p>
				</td>
				<td>
					<p style="height: 0px;text-align:left;">Hours:</p>
				</td>
				<td>
					<p style="height: 0px;text-align:left;">WorkoutName:</p>
				</td>
    		</tr>
	    	<tr>
				<td>
					<input type="date" v-model="date" v-on:blur = "validateDate" style="font-size: 25px; width: 250px;margin-right:30px;"/>
				</td>
				<td>
					<input type="number" v-model="hour" v-on:blur = "validateHour" placeholder="Time(hour)" min="7" max="20" style="font-size: 25px; width:150px;margin-right:50px;"/>
				</td>
				<td>
					<p style="width: 500px;font-size: 25px;text-align:left;height: 25px;">{{selectedPersonalWorkout.name}}</p>
				</td>
				<td>
					<button v-on:click="CheckingPersonalWorkout()" :disabled="cantSubmit" style="font-size: 20px; width: 400px; margin: 0px 10px;">Check workout</button>
				</td>
			</tr>
    	</table>
    	<table>
			<td>
				<td style="max-width:285px;min-width:285px">
					<p style="font-size:20px;margin-top:-20px;margin-right:35px;" v-show=dateNotChecked>You should enter valid date!</p>
				</td>
				<td>
					<p style="font-size:20px;margin-top:-20px;" v-show=hourNotValid>You should enter hours between 7-20!</p>
				</td>
			</tr>
    	</table>
	</td>
	<td v-if="checkedGroup">
		<p>Possible Group trainings</p>
		<table border="3" style="margin-left:auto;margin-right:auto;height:50%;width:1316px;display:block;font-size:25px;margin-top:-15px;">
			<thead style="width: 100%;height: 29px; display: inline-block;margin-right:40px;">
	    		<tr bgcolor="grey" style="width:100%;font-size: 20px;">
	    			<th style="max-width:170px;min-width:170px;">Name</th>
	    			<th style="max-width:300px;min-width:300px">Image</th>
	    			<th style="max-width:170px;min-width:170px;">Coach</th>
	    			<th style="max-width:170px;min-width:170px;">Description</th>
	    			<th style="max-width:110px;min-width:110px;">Duration</th>
	    			<th style="max-width:140px;min-width:140px;">Price</th>
	    			<th style="max-width:140px;min-width:140px;">Date</th>
	    			<th style="max-width:60px;min-width:60px;">Hour</th>
	    		</tr>
    		</thead>
    		<tbody style="width: calc(100% + 20px);height: 400px;display: inline-block; overflow: auto;" class="showa">
	    		<tr v-for="(object, index) in this.groupContents" v-on:click="SelectGroup(object)">
	    			<td style="max-width:170px;min-width:170px">{{object.workout}}</td>
	    			<td style="max-width:300px;min-width:300px"><img :src="object.image" style="width:200px; height:200px;"></td>
	    			<td style="max-width:170px;min-width:170px">{{object.coachUsername}}</td>
	    			<td style="max-width:170px;min-width:170px">{{object.description}}</td>
	    			<td style="max-width:110px;min-width:110px">{{object.duration}}</td>
	    			<td style="max-width:140px;min-width:140px;">{{object.price}}</td>
	    			<td style="max-width:140px;min-width:140px">{{object.checkinDate}}</td>
	    			<td style="max-width:60px;min-width:60px">{{object.hours}}</td>
	    		</tr>
    		</tbody>
    	</table>
    	<table>
	    	<tr>
				<td style="width:250px">
				<p style="font-size: 25px;text-align:left;height: 25px;">Checking date: {{selectedGroupWorkout.checkinDate}}</p>
				</td>
				<td style="width:100px">
					<p style="font-size: 25px;text-align:left;height: 25px;">Hours: {{selectedGroupWorkout.hours}}</p>
				</td>
				<td style="width:220px">
					<p style="font-size: 25px;text-align:left;height: 25px;">Workout name: {{selectedGroupWorkout.workout}}</p>
				</td>
				<td style="width: 300px;">
				</td>
				<td>
					<button v-on:click="CheckingGroupWorkout()" :disabled="cantGroupSubmit" style="font-size: 20px; width: 400px; margin: 0px 10px;">Check workout</button>
				</td>
			</tr>
    	</table>
	</td>
    </table>
</div> 
`
	, 
	methods : {
		init : function() {
		},
		checkCanConfirm: function(){
			if(!this.workoutNotChecked & !this.dateNotChecked & !this.hourNotValid)
			{
				this.cantSubmit = false;
			}
			else this.cantSubmit = true;
		},
		validateDate: function(){
			let dateString = this.date;
			let date = new Date(this.date);
			let todayplus2 = new Date();
			todayplus2.setDate(todayplus2.getDate() + 2)
			dateString = dateString + "e";
			if (!(dateString == "e"))
			{
				if(date > todayplus2)
					this.dateNotChecked = false;
				else{
					this.dateNotChecked = true;
				}
			}
			else
			{
				this.dateNotChecked = true;
			}
			this.checkCanConfirm();
		},
		validateHour: function(){
			let hour = this.hour;
			hour = hour + "e";
			if (!(hour == "e"))
			{
				if(this.hour >= 7 & this.hour <= 20){
					this.hourNotValid = false;
				}else{
					this.hourNotValid = true;
				}
			}
			else
			{
				this.hourNotValid = true;
			}
			this.checkCanConfirm();
		},
		validateName: function(){
			let name = this.selectedPersonalWorkout.name;
			name = name + "e";
			if (!(name == "e"))
			{
				this.workoutNotChecked = false;
			}
			else
			{
				this.workoutNotChecked = true;
			}
			this.checkCanConfirm();
		},
		CheckPersonal : function() {
			this.checkedPersonal = true;
			this.checkedGroup = false;
		},
		afterAdding : function(data) {
			if(data == "Success"){
				alert("Workout successfuly added!");
				router.push(`/`);
			} else {
				alert(data);
			}
		},
		CheckingPersonalWorkout : function() {
			axios
	          .post('workoutHistory/add',null,{params: {workoutName: '' + this.selectedPersonalWorkout.name, date: '' + this.date, hour: '' + this.hour}})
	          .then(response => (this.afterAdding(response.data)));
		},
		CheckingGroupWorkout : function() {
			this.selectedGroupWorkout.checkinDate = this.selectedGroupWorkout.checkinDate.split("/").reverse().join("-");
			axios
	          .post('workoutHistory/addGroup',null,{params: {workoutName: '' + this.selectedGroupWorkout.workout, date: '' + this.selectedGroupWorkout.checkinDate, hour: '' + this.selectedGroupWorkout.hours}})
	          .then(response => (this.afterAdding(response.data)));
		},
		CheckGroup : function() {
			this.checkedPersonal = false;
			this.checkedGroup = true;
		},
		SelectPersonal : function(object) {
			this.selectedPersonalWorkout = object;
			this.validateName();
		},
		SelectGroup : function(object) {
			this.selectedGroupWorkout = object;
			this.cantGroupSubmit = false;
		},
		initialisePersonalContents : function (data) {
			this.personalContents = data;
		},
		initialiseGroupContents : function (data) {
			this.groupContents = data;
				for (const i in this.groupContents){
				this.groupContents[i].checkinDate = this.groupContents[i].checkinDate.split("-").reverse().join("/");
			}
		}
	},
	mounted () {
				axios
			.get('workout/getAllByCustomer')
			.then(response => (this.initialisePersonalContents(response.data)));
			
						axios
			.get('ActiveGroupworkout/getAllByCustomer')
			.then(response => (this.initialiseGroupContents(response.data)));
    }
});