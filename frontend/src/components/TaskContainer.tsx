import { Box, Button, Text } from "@chakra-ui/react";
import React from "react";

const TaskContainer = () => {
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
        <Text fontSize="md" color="#000">Hello World</Text>
        <Box
          display="flex"
          flexDirection="row"
          width="98%"
          alignItems="center"
          mt={2}
        >
            <Box width="80%" paddingRight={2}>
            <Text fontSize="sm" color="#000">This is a description about</Text>
          </Box>

          {/* Button (20% width) */}
          <Box width="20%">
            <Button width="100%" variant="outline" color="#000" size="xs" _hover={{backgroundColor:"#fff"}}>
              Done
            </Button>
            
          </Box>
        </Box>
      </Box>
    </>
  );
};

export default TaskContainer;
