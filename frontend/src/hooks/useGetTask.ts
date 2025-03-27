import { useQuery } from "@tanstack/react-query";
import axios, { AxiosError } from "axios"

export interface Task{
    taskId:number,
    title:string;
    description:string
    completed:string
}


interface SuccessResponse{
    dataList:Task[]
}

interface ErrorResponse{
    errorMessage:string
}

const useGetTask=()=>{

    const getExerciseList=async()=>{
        try{
        const {data}=await axios.get<SuccessResponse>("http://localhost:4000/v1/api/todo/")
        console.log(data.dataList);
        return data.dataList;
        }catch(e){
            if(e instanceof AxiosError){
                const error=((e.response?.data) as ErrorResponse).errorMessage || "Request failed"
                throw new Error(error)
                        }
            console.log(e)
                throw new Error("Un expected error occured")
        }
    }

    return useQuery<Task[],Error>({
        queryKey:["taskList"],
        queryFn:getExerciseList
    })

}

export default useGetTask