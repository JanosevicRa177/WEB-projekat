Vue.component("showSportBuildingContentByManager", {
	data: function () {
		return {
			Contents: null,
		}
	},
	template: ` 
<div style="text-align:center;">
	<table style="margin-left:auto; margin-right:auto;" >
		<td style="padding: 0 30px;">
			<div style="text-align:center;">
	        	<h2>Contents:</h2>
	    		<table border="3" style="margin-left:auto;margin-right:auto;height:50%;width:1162px;display:block;font-size:25px">
	    			<thead style="width: 100%;height: 29px; display: inline-block;margin-right:40px;">
			    		<tr bgcolor="grey" style="width:100%;font-size: 20px;">
			    			<th style="max-width:170px;min-width:170px;">Name</th>
			    			<th style="max-width:140px;min-width:140px">Type</th>
			    			<th style="max-width:300px;min-width:300px">Image</th>
			    			<th style="max-width:170px;min-width:170px;">Coach</th>
			    			<th style="max-width:170px;min-width:170px;">Description</th>
			    			<th style="max-width:170px;min-width:170px;">Duration</th>
			    		</tr>
		    		</thead>
		    		<tbody style="width: calc(100% + 20px);height: 400px;display: inline-block; overflow: auto;" class="showa">
			    		<tr v-for="(object, index) in this.Contents" v-on:click="ChangeContent(object)">
			    			<td style="max-width:170px;min-width:170px">{{object.name}}</td>
			    			<td style="max-width:140px;min-width:140px">{{object.type}}</td>
			    			<td style="max-width:300px;min-width:300px"><img :src="object.image" style="width:200px; height:200px;"></td>
			    			<td style="max-width:170px;min-width:170px">{{object.coachUsername}}</td>
			    			<td style="max-width:170px;min-width:170px">{{object.description}}</td>
			    			<td style="max-width:170px;min-width:170px">{{object.duration}}</td>
			    		</tr>
		    		</tbody>
		    	</table>
		    	<h3>Click on content to chage it</h3>
		    </div>
		</td>
	</table>
</div> 
`
	,
	methods : {
		ChangeContent : function (object) {
			router.push({ path: '/changeContent', query: { ContentName: object.name } });
		},
		initialiseContents : function (data) {
		this.Contents = data;
		}
	},
	mounted () {
		axios
			.get('workout/getAllByManager')
			.then(response => (this.initialiseContents(response.data)));
    }
});