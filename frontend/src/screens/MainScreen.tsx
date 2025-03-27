import {
  Box,
  Button,
  Grid,
  GridItem,
  Input,
  Text,
  Textarea,
} from "@chakra-ui/react";

const MainScreen = () => {
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
                <Input
                  placeholder="Title"
                  style={{ border: "2px solid #D3D3D3", borderRadius: 5 }}
                  width="100%"
                  mt={10}
                />
                <Textarea
                  placeholder="Description"
                  width="100%"
                  height={20}
                  mt={10}
                  style={{ border: "2px solid #D3D3D3", borderRadius: 5 }}
                />
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
                    _hover={{
                      backgroundColor: "#A6A9D3", 
                    }}
                  >
                    Add
                  </Button>
                </Box>
              </Box>
            </GridItem>
            <GridItem colSpan={1}></GridItem>
          </Grid>
        </Box>
      </Box>
    </>
  );
};

export default MainScreen;
