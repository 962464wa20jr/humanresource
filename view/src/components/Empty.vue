<template>
     <div>
      <Login></Login>
      <br />
      <Main></Main> 	
     </div>
</template>
<script>
   import Login from '@/pages/Login';
   import Main from '@/pages/Main';
   export default {
   	components:{
   		Login,
   		Main
   	},
   	data(){
   		return {
   			currentUser:""
   		}
   	},
   	methods:{
   		
   	}
   }
</script>
