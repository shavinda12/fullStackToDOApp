import { useMutation } from "@tanstack/react-query"
import axios, { AxiosError } from "axios"
import { Task } from "./useGetTask"




interface SuccessResponse{
    message:string
}

interface ErrorResponse{
    errorMessage:string
}

const useUpdateTask=()=>{
    
    return useMutation<string,Error,number>({
        mutationFn:async(taskId:number)=>{
            try{
              const {data}=await axios.put<SuccessResponse>(`http://localhost:4000/v1/api/todo/${taskId}`)
              console.log(data)
              return data.message
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

export default useUpdateTask;