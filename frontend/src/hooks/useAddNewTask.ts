import { useMutation } from "@tanstack/react-query"
import axios, { AxiosError } from "axios"
import { Task } from "./useGetTask"


export interface PostTask{
   title:string,
   description:string
}

interface SuccessResponse{
    data:Task
}

interface ErrorResponse{
    errorMessage:string
}

const useAddNewTask=()=>{
    
    return useMutation<Task,Error,PostTask>({
        mutationFn:async(taskData:PostTask)=>{
            try{
              const {data}=await axios.post<SuccessResponse>("http://localhost:4000/v1/api/todo/",{
                title:taskData.title,
                description:taskData.description,
                completed:false
              })
              console.log(data)
              return data.data
        }catch(e){
            if(e instanceof AxiosError){
                const error=((e.response?.data) as ErrorResponse).errorMessage||"Request failed"
                console.log("catch error in catch block",error)
                throw new Error(error);      
            }
            console.log(e)
            throw new Error("un expected error occured")    
        }

        }
    })

}

export default useAddNewTask;