Vue.component("showBuilding", {
	data: function () {
		    return {
				sportBuilding: {"name":"","type":"Gym","status":"","location":{"longitude":0,"latitude":0,"address":{"street":"","number":"","city":"","zipCode":""}},"image":"","averageGrade":7.0,"workTime":""},
				checkedComments: true,
				checkedWorkouts: false,
				Workouts: [],
				Comments: [],
				canComment: false,
				commentContent: "",
				commentGrade: "",
		      	commentContentNotValid: true,
		      	commentGradeNotValid: true,
		      	cantSubmit: true,
		    }
	},
	template: ` 
<div style="text-align:center;">
	<table style="margin-left:auto;margin-top:10px; margin-right:auto;">
		<td style="width:450px;">
			<tr style="font-size:75px">{{this.sportBuilding.name}}<p style="font-size:30px"/></tr>
			<tr> Type:</tr>
			<tr>{{this.sportBuilding.type}}</tr>
		</td>
		<td style="width:200px;">
			<tr> Status:</tr>
			<tr>{{this.sportBuilding.status}}<p/></tr>
			<tr> Grade:</tr>
			<tr>{{this.sportBuilding.averageGrade}}</tr>
		</td>
		<td style="width:360px;">
			<tr>
			Address:
			</tr>
			<tr style="line-height: 0.4;">
				<p>{{this.sportBuilding.location.longitude}}, {{this.sportBuilding.location.latitude}}</p>
				<p>{{this.sportBuilding.location.address.street}}, {{this.sportBuilding.location.address.number}}</p>
				<p>{{this.sportBuilding.location.address.city}}, {{this.sportBuilding.location.address.zipCode}}</p>
			</tr>
		</td>
		<td>
			<div>
			<tr>Image:</tr>
			<tr style="max-width:200px;min-width:200px"><img :src="this.sportBuilding.image" style="width:150px; height:150px;"></tr>
			</div>
		</td>
	</table>
	<table class="checkbar" style="text-align:center;width:calc(100% + 60px);height: 50px;margin-left:-30px;">
		<td style="text-align:center;width:50%;">
			<button style="text-align:center;width:100%;" class ="checkButtons" v-on:click="CheckWorkouts">Workouts</button>
		</td>
		<td style="text-align:center;width:50%;">
 			<button style="text-align:center;width:100%;" class ="checkButtons" v-on:click="CheckComments">Comments</button>
		</td>
    </table>
    <div>
	    <table style="margin-left:auto; margin-right:auto;">
		    <td v-if="checkedComments"> 
			    <div v-if="canComment">
				    <table>
				    	<td style="width:1090px;text-align: left;">
				    		<tr>Comment:</tr>
				    		<tr><textarea style="font-size: 25px;resize: none; width: 1030px;height:200px;" v-on:change = "validateContent" v-model="commentContent"></textarea></tr>
				    	</td>
				    	<td style="width:170px;text-align: center;">
				    		<tr>grade(0-10):</tr>
				    		<tr><input type="number" style="font-size: 25px; width: 80px" v-model="commentGrade" v-on:change = "validateGrade"></tr>
				    	</td>
				    	<td rowspan="2">
				    		<tr rowspan="2"><button :disabled="cantSubmit" v-on:click="Comment()" style="text-align:center;width:250px;position: absolute;top: 50%;transform: translateY(-50%);-ms-transform: translateY(-50%);font-size:25px;">Comment</button></tr>
				    	</td>
				    </table>
			    </div>
		    	<table style="margin-left:auto;margin-top:-30px; margin-right:auto;" >
					<td style="padding: 0 30px;">
						<div style="text-align:center;">
				        	<h2 style="height: 40px;">Comments:</h2>
				    		<table border="3" style="margin-left:auto;margin-right:auto;height:50%;width:1550px;display:block;font-size:25px;margin-top:-10px;">
					    		<thead style="width: 100%;height: 29px; display: inline-block;margin-right:40px;">
						    		<tr bgcolor="grey" style="width:100%;font-size: 20px;">
						    			<th style="max-width:170px;min-width:170px;">customer</th>
						    			<th style="max-width:1253px;min-width:1253px">comment</th>
						    			<th style="max-width:100px;min-width:100px">grade</th>
						    		</tr>
					    		</thead>
					    		<tbody style="width: calc(100% + 20px);display: inline-block; overflow: auto;" class="showa">
						    		<tr v-for="(object, index) in this.Comments">
						    			<td style="max-width:170px;min-width:170px">{{object.customer}} </td>
						    			<td style="max-width:1253px;min-width:1253px">{{object.comment}}</td>
						    			<td style="max-width:100px;min-width:100px">{{object.grade}}</td>
						    		</tr>
					    		</tbody>
					    	</table>
					    </div>
					</td>
				</table>
		    </td>
		    <td v-if="checkedWorkouts">
		    	<table style="margin-left:auto;margin-top:-30px; margin-right:auto;" >
					<td style="padding: 0 30px;">
						<div style="text-align:center;">
				        	<h2 style="height: 40px;">Workouts:</h2>
				    		<table border="3" style="margin-left:auto;margin-right:auto;height:50%;width:1309px;display:block;font-size:25px;margin-top:-10px;">
				    			<thead style="width: 100%;height: 29px; display: inline-block;margin-right:40px;">
						    		<tr bgcolor="grey" style="width:100%;font-size: 20px;">
						    			<th style="max-width:170px;min-width:170px;">Name</th>
						    			<th style="max-width:140px;min-width:140px">Type</th>
						    			<th style="max-width:300px;min-width:300px">Image</th>
						    			<th style="max-width:170px;min-width:170px;">Coach</th>
						    			<th style="max-width:170px;min-width:170px;">Description</th>
						    			<th style="max-width:170px;min-width:170px;">Duration</th>
						    			<th style="max-width:140px;min-width:140px;">Price</th>
						    		</tr>
					    		</thead>
					    		<tbody style="width: calc(100% + 20px);height: 310px;display: inline-block; overflow: auto;" class="showa">
						    		<tr v-for="(object, index) in this.Workouts">
						    			<td style="max-width:170px;min-width:170px">{{object.name}}</td>
						    			<td style="max-width:140px;min-width:140px">{{object.type}}</td>
						    			<td style="max-width:300px;min-width:300px"><img :src="object.image" style="width:200px; height:200px;"></td>
						    			<td style="max-width:170px;min-width:170px">{{object.coachUsername}}</td>
						    			<td style="max-width:170px;min-width:170px">{{object.description}}</td>
						    			<td style="max-width:170px;min-width:170px">{{object.duration}}</td>
						    			<td style="max-width:140px;min-width:140px;">{{object.price}}</td>
						    		</tr>
					    		</tbody>
					    	</table>
					    </div>
					</td>
				</table>
		    </td>
	    </table>
    </div>
</div> 
`
	, 
	methods : {
		init : function() {
		},
		checkCanConfirm: function(){
			if(!this.commentContentNotValid && !this.commentGradeNotValid)
			{
				this.cantSubmit = false;
			}
			else this.cantSubmit = true;
		},
		validateContent: function(){
			let Content = this.commentContent;
			Content = Content + "e";
			if(!(Content === "e"))
			{
				this.commentContentNotValid = false;
			} else {
				this.commentContentNotValid = true;
			}
			this.checkCanConfirm();
		},
		validateGrade: function(){
			let Grade = this.commentGrade;
			Grade = Grade + "e";
			if(!(Grade === "e"))
			{
				if(this.commentGrade <= 10 && this.commentGrade >= 0){
					this.commentGradeNotValid = false;
				} else {
					this.commentGradeNotValid = true;	
				}
			} else {
				this.commentGradeNotValid = true;
			}
			this.checkCanConfirm();
		},
		afterCommenting : function(data) {
			if(data == "Success"){
				this.canComment = false;
				alert("You succesfully commented on " + this.sportBuilding.name + " page!");
			} else {
				alert(data);
			}
		},
		Comment : function() {
			axios
			.post('/comment/comment',null,{params: {sportBuildingName: '' + this.$route.query.sportBuildingName, Grade: '' + this.commentGrade, Comment: '' + this.commentContent}})
	        .then(response => (this.afterCommenting(response.data)));
		},
		CheckWorkouts : function() {
			this.checkedComments = false;
			this.checkedWorkouts = true;
		},
		CheckComments : function() {
			this.checkedComments = true;
			this.checkedWorkouts = false;
		},
	},
	mounted () {
		axios
		.get('sportBuilding/get',{params: {sportBuildingName: '' + this.$route.query.sportBuildingName}})
        .then(response => (this.sportBuilding = response.data));
		axios
		.get('workout/getAllBySportBuilding',{params: {sportBuildingName: '' + this.$route.query.sportBuildingName}})
		.then(response => (this.Workouts = response.data));
		axios
		.get('comment/getCommentsBySportBuilding',{params: {sportBuildingName: '' + this.$route.query.sportBuildingName}})
		.then(response => (this.Comments = response.data));
		axios
		.get('comment/checkCustomer',{params: {sportBuildingName: '' + this.$route.query.sportBuildingName}})
		.then(response => (this.canComment = !response.data));
    }
});