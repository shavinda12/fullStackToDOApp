import useUpdateTask from "@/hooks/useUpdateTask";
import { Box, Button, Text } from "@chakra-ui/react";

interface TaskContainerProps{
    title:string,
    description:string,
    taskId: number, 
   onTaskUpdated: (message: string) => void;
}



const TaskContainer = ({title,description,taskId,onTaskUpdated}:TaskContainerProps) => {

    const updateTask=useUpdateTask();

    const handleDone=()=>{
        updateTask.mutate(taskId,{
            onSuccess:(data)=>{
                onTaskUpdated(data)
            },
            onError:(error)=>{
                onTaskUpdated("Internal Server Error")
            }
        })
    }



  return (
    <>
      <Box
        width="100%"
        backgroundColor="#C0C0C0"
        display="flex"
        flexDirection="column"
        alignItems="flex-start"
        borderRadius={10}
        pl={5}
        pt={2}
        pb={2}
        pr={5}
        mt={2}
      >
        <Text fontSize="md" color="#000">{title}</Text>
        <Box
          display="flex"
          flexDirection="row"
          width="98%"
          alignItems="center"
          mt={2}
        >
            <Box width="80%" paddingRight={2}>
            <Text fontSize="sm" color="#000">{description}</Text>
          </Box>

          {/* Button (20% width) */}
          <Box width="20%">
            <Button width="100%" variant="outline" color="#000" size="xs" _hover={{backgroundColor:"#fff"}} onClick={handleDone}>
              Done
            </Button>
            
          </Box>
        </Box>
      </Box>
    </>
  );
};

export default TaskContainer;
