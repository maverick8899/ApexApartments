import { Box, Container, Text, Tabs, TabList, TabPanels, Tab, TabPanel } from '@chakra-ui/react';
import Login from '../components/Authentication/Login';
import SignUp from '../components/Authentication/SignUp';

const Home = () => {
    return (
        <Container centerContent style={{ background: 'none' }}>
            <Box
                display={'flex'}
                justifyContent={'center'}
                p={'3'}
                w={'400px'}
                borderRadius={'lg'}
                bg={'#79E0EE'}
                m={'40px 0 15px 0'}
            >
                <Text fontSize={'2xl'}>Message</Text>
            </Box>
            <Box bg={'#79E0EE'} w={'400px'} p={4} borderRadius={'lg'}>
                <Tabs variant="soft-rounded">
                    <TabList mb={'1em'}>
                        <Tab borderBlockEndColor={'whitesmoke'} width={'50%'}>
                            Login
                        </Tab>
                        <Tab borderBlockEndColor={'whitesmoke'} width={'50%'}>
                            Sign up
                        </Tab>
                    </TabList>
                    <TabPanels>
                        <TabPanel>
                            <Login />
                        </TabPanel>
                        <TabPanel>
                            <SignUp />
                        </TabPanel>
                    </TabPanels>
                </Tabs>
            </Box>
        </Container>
    );
};
//TabList và TabPanel được liên kết với nhau theo thứ tự
export default Home;
