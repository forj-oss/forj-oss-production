multibranchPipelineJob('forjj-repotemplates') {
  description('Folder for Project forjj-repotemplates generated and maintained by Forjj. To update it use forjj update')
  branchSources {
      github {
          apiUri('https://api.github.com')
          includes('*')
          repoOwner('forj-oss')
          repository('forjj-repotemplates')
      }
  }
  orphanedItemStrategy {
      discardOldItems {
          numToKeep(20)
      }
  }
}
