Vue.component("changeContent", {
	data: function () {
		    return {
		      workout: {name:"",type:"Personal",image:"",coachUsername:"",duration:"",description:"",price:""},
		      cantSubmit: false,
		      nameNotValid: false,
		      imageNotValid: false,
		      isWorkout: true,
		      coaches:[]
		    }
	},
	template: ` 
<div style="text-align:center;margin-top:-20px;">
    <h2 style="font-size: 55px;">Content change</h2>
        <table style="margin-left:auto; margin-right:auto;">
	        <tr>
	            <td align="left"><strong style="font-size: 30px;">Content name:</strong></td>
	            <td><input type="text" v-model="workout.name" style="font-size: 25px;width: 342px;" v-on:change = "validateName" name="name" disabled></input></td>
	            <td rowspan="6">
	            	<table style="margin-left:100px;">
           				<tr align="center"><p style="font-size:20px;">Image preview</p></tr>
	        			<tr align="center" style="width:200px;"><img :src="workout.image" style="width:150px; height:150px;"></tr>
	            	</table>
	            </td>
	        </tr>
	       		<td align="left"><strong style="font-size: 30px;">Content type:</strong></td>
	            <td>
		            <select name="type" id="type" v-model="workout.type" v-on:click="CheckType" style="font-size: 25px; width: 350px;">
		            	<option value="Group">Group</option>
		            	<option value="Personal">Personal</option>
		            	<option value="Gym">Gym</option>
		            	<option value="Sauna">Sauna</option>
		            </select>
	            </td>
	        </tr>
	        <tr v-if="isWorkout">
	        	<td align="left"><strong style="font-size: 30px;">Coach:</strong></td>
	            <td>
		            <select name="sportB" id="sportB" v-model="workout.coachUsername" style="font-size: 25px; width: 350px;">
		            	<option v-for="(object, index) in this.coaches">{{object.username}}</option>
		            </select>
	            </td>
	        </tr>
        	<tr>
            	<td align="left"><strong style="font-size: 30px;">Image (Url link):</strong></td>
           		<td><input type="text" v-model="workout.image" style="font-size: 25px;width: 342px;" v-on:change = "validateImage" name="image"></input></td>
        	</tr>
	        <tr>
		        <td align="left"><strong style="font-size: 30px;">Description:</strong></td>
		        <td><input type="text" v-model="workout.description" style="font-size: 25px;width: 342px;" name="description"></input></td>
	        </tr>
	        <tr>
		        <td align="left"><strong style="font-size: 30px;">Duration (minutes):</strong></td>
		        <td><input type="number" v-model="workout.duration" style="font-size: 25px;width: 342px;" name="duration"></input></td>
	        </tr>
	        <tr>
		        <td align="left"><strong style="font-size: 30px;">Price:</strong></td>
		        <td><input type="number" v-model="workout.price" style="font-size: 25px;width: 342px;" name="duration"></input></td>
	        </tr>
        	<tr>
	        	<td colspan="3">
        			<a href="https://im.ge/?gclid=CjwKCAjw2f-VBhAsEiwAO4lNeDzIWfTlizCneY4CibiS69bzO04mt6nzGFDEdrYMy5HgpjrQ0o8jXhoC-YMQAvD_BwE" target="_blank"> You can upload photo and get url link of it here </a>
        		</td>
        	</tr>
	        <tr style="height:70px;margin-top:50px;margin-left:50px;">
	        	<td colspan="3">
		        	<button v-on:click="addWorkout()" :disabled="cantSubmit" style="font-size: 25px; width: 30%;margin: 0px 10px;"> Submit </button> 
		        	<button v-on:click="back()"style="font-size: 25px; width: 30%; margin: 0px 10px;">Back</button>
	        	</td>
        	</tr>
    </table>
    	<div style="text-align:left;margin-left:auto; margin-right:auto;">
			<p style="font-size:20px;" v-show=nameNotValid>You should enter content name</p>
			<p style="font-size:20px;" v-show=imageNotValid>You should enter content image</p>
		</div>
</div> 
`
	, 
	methods : {
		checkCanConfirm: function(){
			if(!this.nameNotValid & !this.imageNotValid)
			{
				this.cantSubmit = false;
			}
			else this.cantSubmit = true;
		},
		back : function () {
			router.push(`/manager/showContents`);
		},
		addWorkout : function () {
			if(this.workout.duration == ""){
				this.workout.duration = "nedefinisano vreme";
			}
			if(this.workout.description == ""){
				this.workout.description = "none";
			}
			if(this.workout.price == ""){
				this.workout.price = "0";
			}
				axios
		      .put('workout/change',this.workout)
		      .then(response => (this.afterChanging(response.data)));
		},
		afterChanging : function (data) {
			if(data == "Success"){
			alert("Workout updated successfuly!");
			router.push(`/manager/showContents`);	
			} else {
				alert(data);
			}
		},
		validateName: function(){
			let name = document.getElementsByName('name')[0].value;
			name = name + "e";
			if(name === "e")
			{
				this.nameNotValid = true;
			}
			else
			{
				this.nameNotValid = false;
			}
			this.checkCanConfirm();
		},
		validateImage: function(){
			let image = document.getElementsByName('image')[0].value;
			image = image + "e";
			if(image === "e")
			{
				this.imageNotValid = true;
			}
			else
			{
				this.imageNotValid = false;
			}
			this.checkCanConfirm();
		},
		CheckType: function(){
			if(this.workout.type == "Group" || this.workout.type == "Personal"){
				this.isWorkout = true;
				this.workout.coachUsername = this.coaches[0].username;	
			}
			else {
				this.isWorkout = false;
				this.workout.coachUsername = "None";
			}
		},
		initCoaches : function (data) {
		    this.coaches = data;
		},
		initWorkout : function (data) {
		    this.workout = data;
		    if(this.workout.description == "none"){
				this.workout.description = ""
			}
			if(this.workout.duration == "nedefinisano vreme"){
				this.workout.duration = "";
			}
		    this.CheckType();
		}
	},
	mounted () {
		axios
  		.get('coach/getAll')
  		.then(response => (this.initCoaches(response.data)));
  		axios
  		.get('workout/getByName',{params: {ContentName: '' + this.$route.query.ContentName}})
  		.then(response => (this.initWorkout(response.data)));
    }
});