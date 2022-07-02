Vue.component("createContent", {
	data: function () {
		    return {
		      workout: {name:null,type:"Personal",image:null,coachName:null,duration:"",description:""},
		      cantSubmit: true,
		      nameNotValid: true,
		      imageNotValid: true,
		      isWorkout: true,
		      coaches:[]
		    }
	},
	template: ` 
<div style="text-align:center;">
    <h2 style="font-size: 55px;">Content registration</h2>
        <table style="margin-left:auto; margin-right:auto;">
	        <tr>
	            <td align="left"><strong style="font-size: 30px;">Content name:</strong></td>
	            <td><input type="text" v-model="workout.name" style="font-size: 25px;width: 342px;" v-on:change = "validateName" name="name"></input></td>
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
		            <select name="sportB" id="sportB" v-model="workout.coachName" style="font-size: 25px; width: 350px;">
		            	<option v-for="(object, index) in this.coaches">{{object.username}}</option>
		            </select>
	            </td>
	        </tr>
        	<tr>
            	<td align="left"><strong style="font-size: 30px;">Image:</strong></td>
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
	        	<td colspan="2">
		        	<a href="https://im.ge/?gclid=CjwKCAjw2f-VBhAsEiwAO4lNeDzIWfTlizCneY4CibiS69bzO04mt6nzGFDEdrYMy5HgpjrQ0o8jXhoC-YMQAvD_BwE" target="_blank"> You can upload photo and get url link of it here </button>
	        	</td>
        	</tr>
	        <tr style="height:70px">
	        	<td colspan="2">
		        	<button v-on:click="addWorkout()" :disabled="cantSubmit" style="font-size: 25px; width: 42%;margin: 0px 10px;"> Submit </button> 
		        	<button v-on:click="back()"style="font-size: 25px; width: 42%; margin: 0px 10px;">Back main page</button>
	        	</td>
        	</tr>
    </table>
    <div style="text-align:left;">
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
			router.push(`/`);
		},
		addWorkout : function () {
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
			let name = document.getElementsByName('image')[0].value;
			name = name + "e";
			if(name === "e")
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
				this.workout.coachName = this.coaches[0].username;	
			}
			else {
				this.isWorkout = false;
				this.workout.coachName = "None";
			}
		},
		init : function() {
		},
		initCoaches : function (data) {
		    this.coaches = data;
		    this.workout.coachName = this.coaches[0].username;
		}
	},
	mounted () {
		axios
	      .get('coach/getAll')
	      .then(response => (this.initCoaches(response.data)));
    }
});