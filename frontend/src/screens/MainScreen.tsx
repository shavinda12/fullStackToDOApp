import {
  Box,
  Button,
  Grid,
  GridItem,
  Input,
  Text,
  Textarea,
} from "@chakra-ui/react";
import { useForm } from "react-hook-form";
import { z } from "zod";
import { zodResolver } from "@hookform/resolvers/zod";
import TaskContainer from "@/components/TaskContainer";


const formSchema = z.object({
  title: z.string().min(1, "Title is required").max(100, "Title is too long"),
  description: z.string().min(1, "Description is required"),
});

type FormData = {
  title: string;
  description: string;
};

const MainScreen = () => {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<FormData>({
    resolver: zodResolver(formSchema),
  });

  const onSubmit = (data: FormData) => {
    console.log(data); // Logs the form data when validation is successful
  };

  return (
    <>
      <Box
        height={"100vh"}
        width={"100vw"}
        backgroundColor="#E5E5E5"
        style={{
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
        }}
      >
        <Box
          height="90%"
          width="90%"
          backgroundColor="#fff"
          border="20px solid #DADADA"
          borderRadius={20}
          padding={5}
        >
          <Grid templateColumns="repeat(2, 1fr)" height="85%" marginTop={10}>
            <GridItem
              colSpan={1}
              borderRight={"5px solid #D3D3D3"}
              width="100%"
              p={2}
              style={{
                display: "flex",
                flexDirection: "row",
                justifyContent: "center",
                alignItems: "flex-start",
              }}
            >
              <Box
                width={["100%", "100%", "100%", "60%"]}
                style={{
                  display: "flex",
                  flexDirection: "column",
                  justifyContent: "flex-start",
                  alignItems: "flex-start",
                }}
              >
                <Text textStyle="lg" color="#000">
                  Add a Task
                </Text>
                <form onSubmit={handleSubmit(onSubmit)} style={{width:"100%"}}>  
                  <Input
                    {...register("title")}
                    placeholder="Title"
                    style={{ border: "2px solid #D3D3D3", borderRadius: 5 }}
                    width="100%"
                    mt={10}
                  />
                  {errors.title && (
                    <Text color="red.500" fontSize="sm">{errors.title.message}</Text>
                  )}
                  <Textarea
                    {...register("description")}
                    placeholder="Description"
                    width="100%"
                    height={20}
                    mt={10}
                    style={{ border: "2px solid #D3D3D3", borderRadius: 5 }}
                  />
                  {errors.description && (
                    <Text color="red.500" fontSize="sm">{errors.description.message}</Text>
                  )}

                  <Box
                    display="flex"
                    flexDirection="row"
                    justifyContent="flex-end"
                    width="100%"
                    mt={10}
                  >
                    <Button
                      backgroundColor="#6369A4"
                      onClick={() => console.log("hello")}
                      type="submit"
                      _hover={{
                        backgroundColor: "#A6A9D3",
                      }}
                    >
                      Add
                    </Button>
                  </Box>
                </form>
              </Box>
            </GridItem>
            <GridItem colSpan={1} display="flex" flexDirection="row" justifyContent="center" alignItems="center">
                <Box maxHeight="500px" overflowY="auto"
                style={{
                    scrollbarWidth: "none",  // For Firefox
                    msOverflowStyle: "none", // For Internet Explorer
                  }}
                  width="75%"
                  height="100%"
                  justifyContent="flex-start">
                <TaskContainer/>
                <TaskContainer/>
                <TaskContainer/>
                <TaskContainer/>
                <TaskContainer/>
                </Box>
                
            </GridItem>
          </Grid>
        </Box>
      </Box>
    </>
  );
};

export default MainScreen;
